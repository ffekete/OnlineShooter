package game.service;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import game.config.constant.AmmoType;
import game.config.constant.GameConfig;
import game.config.constant.Physics;
import game.controller.EventSender;
import game.datahandler.HighScoreTable;
import game.datahandler.ItemHandler;
import game.datatype.HighScore;
import game.datatype.PlayerData;
import game.interfaces.Ammo;
import game.interfaces.AmmoPoolList;
import game.interfaces.ItemPoolList;
import game.interfaces.PlayerDataProcessorInterface;
import game.interfaces.PlayerPoolMap;
import game.interfaces.SpawnableItem;
import game.scheduler.TaskScheduler;

/** Basic class to calculate player related values e.g. ship angles,... */
public class PlayerDataProcessor implements PlayerDataProcessorInterface {
    @Autowired
    private PlayerPoolMap<Long, PlayerData> playerPool;

    @Autowired
    private AmmoPoolList<Ammo> ammoPool;

    @Autowired
    private ItemPoolList<SpawnableItem> itemPool;

    @Autowired
    private HighScoreTable highScores;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private EventSender eventSender;

    @Autowired
    private CoordinateHandler coordinateHandler;

    @Autowired
    private ItemHandler itemHandler;

    /**
     * Calculates an angle using two points.
     * 
     * @param player
     * @param baseX
     * @param baseY
     * @return
     */
    private double calculateAngleAndFilterIt(PlayerData player, double baseX, double baseY) {
        double angle = Math.toDegrees(Math.atan2(player.getMouseY() - baseY, player.getMouseX() - baseX));
        double previousAngle = player.getPreviousAngle();
        double smoothedValue = previousAngle;

        if (angle < 0) {
            angle += 360;
        }

        if (previousAngle - angle > 180.0) {
            angle = 360.0 + angle;
        }

        else if (angle - previousAngle > 180.0) {
            angle = angle - 360.0;
        }

        smoothedValue += (angle - smoothedValue) / player.getManeuverability();

        if (smoothedValue > 360.0d) {
            smoothedValue -= 360.0d;
        } else if (smoothedValue < 0.0d) {
            smoothedValue += 360.0d;
        }

        return smoothedValue;
    }

    private void checkAmmotHits(PlayerData player) {
        CopyOnWriteArrayList<Ammo> ammo = (CopyOnWriteArrayList<Ammo>) ammoPool.getAllOnScreen(player.getId());

        Iterator<Ammo> ammoIterator = ammo.iterator();

        // true, if player is not invulnerable
        if (player.getInvulnerabilityCounter() < 1L == false) {
            // player is invulnerable, no need to check against the ammo
            return;
        }

        while (ammoIterator.hasNext()) {
            Ammo actualAmmo = ammoIterator.next();

            // the player who shot the ammo cannot hit itself
            if (actualAmmo.getPlayerId() != player.getId()) {
                // true, if ammo hits player
                if (actualAmmo.hits(player.getSpaceShip()) && !actualAmmo.isAlreadyHit()) {
                    actualAmmo.hitDetected(player.getSpaceShip(), eventSender);
                    // true if player is dead
                    if (player.decreaseHp(actualAmmo.getDamage()) < 1L) {

                        // populate death event to client side
                        eventSender.sendItemDestroyedNotification(player.getSpaceShip());

                        PlayerData playerWhoKilledMe = playerPool.get(actualAmmo.getPlayerId());
                        if (player.getIsAI()) {
                            if (player.getIsAsteroid()) {
                                playerWhoKilledMe.increaseScore(GameConfig.ASTEROID_SCORE_VALUE);
                            } else {
                                playerWhoKilledMe.increaseScore(GameConfig.AI_SCORE_VALUE);
                            }
                        } else {
                            /*
                             * Save the killed player only if he/she has more
                             * than 0 points
                             */
                            if (player.getScore() > 0L) {
                                highScores.addScore(new HighScore(player.getScore(), player.getName()));
                            }
                            playerWhoKilledMe.increaseScore(GameConfig.PLAYER_SCORE_VALUE);
                        }
                        highScores.addScore(new HighScore(playerWhoKilledMe.getScore(), playerWhoKilledMe.getName()));
                        itemHandler.dropCargoToCoordinate(player.getSpaceShip());
                        player.kill();
                    } else {
                        eventSender.sendItemHitNotification(player.getSpaceShip());
                    }
                    if(actualAmmo.getType() != AmmoType.LASER_BEAM) {
                        ammoPool.remove(actualAmmo);
                    } else {
                    	actualAmmo.setAlreadyHit(true);
                    }
                }
            }
        }
    }

    private void updatePlayerSpeed(PlayerData player) {
        double canvasHalfWidth = player.getCanvas().getHalfWidth();
        double canvasHalfHeight = player.getCanvas().getHalfHeight();
        double horizontalDistanceFromMidPoint = Math.abs(player.getMouseX() - canvasHalfWidth);
        double verticalDistanceFromMidPoint = Math.abs(player.getMouseY() - canvasHalfHeight);
        double actualDistanceFromScreenMidpoint = Math.sqrt(horizontalDistanceFromMidPoint * horizontalDistanceFromMidPoint
                + verticalDistanceFromMidPoint * verticalDistanceFromMidPoint)
                * GameConfig.MOUSE_SPEED_SENSITIVITY_PERCENT;

        double shorterCanvasValue = canvasHalfWidth > canvasHalfHeight ? canvasHalfHeight : canvasHalfWidth;

        double maxDistance = shorterCanvasValue * GameConfig.MOUSE_SPEED_SENSITIVITY_PERCENT;

        if (actualDistanceFromScreenMidpoint > maxDistance) {
            actualDistanceFromScreenMidpoint = maxDistance;
        }

        double limitation = (actualDistanceFromScreenMidpoint / maxDistance);

        if (limitation < 0.2d) {
            limitation = 0.2d;
        }

        player.setSpeed(player.getMaxSpeed() * limitation);
    }

    private void updateShipAngles(PlayerData player) throws InterruptedException {
        double angle = calculateAngleAndFilterIt(player, (double) player.getCanvas().getHalfWidth(), (double) player.getCanvas().getHalfHeight());
        player.setPreviousAngle(player.getShipAngle());
        player.setShipAngle(angle);
    }

    @Override
    public void updatePlayerData() throws InterruptedException {
        Iterator<Long> shipIds = playerPool.getKeySetIterator();

        while (shipIds.hasNext()) {
            PlayerData player = playerPool.get(shipIds.next());
            if (player.isSpawned()) {
                updateShipAngles(player);
                updatePlayerCoordinates(player);
                updatePlayerSpeed(player);
                updatePlayerCollisions(player);
                checkAmmotHits(player);
                checkIfPlayerGetsAnItem(player);
                player.decreaseInvulnerabilityCounter(1L);
                player.getWeapon().decreaseCooldownValue(1L);
                if (taskScheduler.getTimer() == 0) {
                    // increases shield value in every 5th loop
                    player.increaseShieldPower();
                }
            } else {
                player.decreasePlayerRespawnTime();
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * game.service.PlayerDataProcessorInterface#checkIfPlayerGetsAnItem(game.
     * datatypes.PlayerData)
     */
    @Override
    public void checkIfPlayerGetsAnItem(PlayerData player) {
        CopyOnWriteArrayList<SpawnableItem> items = (CopyOnWriteArrayList<SpawnableItem>) itemPool.getAllOnScreen(player.getId());

        Iterator<SpawnableItem> itemIterator = items.iterator();

        while (itemIterator.hasNext()) {
            SpawnableItem actualItem = itemIterator.next();
            boolean areaCheck = Math.abs(actualItem.getX() - player.getX()) < player.getHitRadius()
                    && Math.abs(actualItem.getY() - player.getY()) < player.getHitRadius();

            if (areaCheck) {
                if (player.getIsAI() && !player.getIsAsteroid()) {
                    player.getSpaceShip().addItemToCargo(actualItem);
                } else if (!player.getIsAsteroid()) {
                    actualItem.applyEffect(player);
                }

                if (!player.getIsAsteroid()) {
                    itemPool.remove(actualItem);
                }
            }
        }
    }

    private void updatePlayerCollisions(PlayerData player1) {
        for (Long j : playerPool.getAll()) {
            PlayerData player2 = playerPool.get(j);
            double hitDistance = player1.getHitRadius() + player2.getHitRadius();
                    && Math.abs(player1.getX() - player2.getX()) <= hitDistance
                    && Math.abs(player1.getY() - player2.getY()) <= hitDistance) {
                player1.setShieldProtection(0L);
                if (player1.decreaseHp(Physics.COLLISION_STRENGTH) < 0L) {
                    eventSender.sendItemDestroyedNotification(player1.getSpaceShip());
                    itemHandler.dropCargoToCoordinate(player1.getSpaceShip());
                    player1.kill();

                }
                player2.setShieldProtection(0L);
                if (player2.decreaseHp(Physics.COLLISION_STRENGTH) < 1L) {
                    eventSender.sendItemDestroyedNotification(player2.getSpaceShip());
                    itemHandler.dropCargoToCoordinate(player2.getSpaceShip());
                    player2.kill();
                }
            }
        }
    }

    /**
     * Updates a given players coordinate.
     */
    private void updatePlayerCoordinates(PlayerData player) {
        player.setCoordinate(
                coordinateHandler.calculateItemCoordinates(player.getSpaceShip(), player.getSpeed(), null));
    }
}
