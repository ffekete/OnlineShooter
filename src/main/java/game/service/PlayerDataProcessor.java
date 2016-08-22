package game.service;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.constant.GameConfig;
import game.config.constant.Physics;
import game.controller.EventSender;
import game.datahandler.HighScoreTable;
import game.datatype.HighScore;
import game.datatype.PlayerData;
import game.interfaces.Bullet;
import game.interfaces.BulletPoolList;
import game.interfaces.ItemPoolList;
import game.interfaces.PlayerDataProcessorInterface;
import game.interfaces.PlayerPoolMap;
import game.interfaces.SpawnableItem;
import game.scheduler.TaskScheduler;

/** Basic class to calculate player related values e.g. ship angles,... */
@Component
public class PlayerDataProcessor implements PlayerDataProcessorInterface {
    @Autowired
    private PlayerPoolMap<Long, PlayerData> playerPool;

    @Autowired
    private BulletPoolList<Bullet> bulletPool;

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

    private void checkBulletHits(PlayerData player) {
        CopyOnWriteArrayList<Bullet> bullets = (CopyOnWriteArrayList<Bullet>) bulletPool.getAllOnScreen(player.getId());

        Iterator<Bullet> bulletIterator = bullets.iterator();

        // true, if player is not invulnerable
        if (player.getInvulnerabilityCounter() < 1L == false) {
            // player is invulnerable, no need to check against the bullets
            return;
        }

        while (bulletIterator.hasNext()) {
            Bullet actualBullet = bulletIterator.next();

            // the player who shot the bullet cannot hit itself
            if (actualBullet.getPlayerId() != player.getId()) {
                // true, if bullet hits player
                if (actualBullet.hits(player.getSpaceShip())) {
                    actualBullet.hitDetected(player.getSpaceShip(), eventSender);
                    // true if player is dead
                    if (player.decreaseHp(actualBullet.getDamage()) < 1L) {

                        // populate death event to client side
                        eventSender.sendItemDestroyedNotification(player.getSpaceShip());

                        PlayerData playerWhoKilledMe = playerPool.get(actualBullet.getPlayerId());
                        if (player.getIsAI()) {
                            playerWhoKilledMe.increaseScore(GameConfig.AI_SCORE_VALUE);
                        } else {
                            playerWhoKilledMe.increaseScore(GameConfig.PLAYER_SCORE_VALUE);
                        }

                        /*
                         * Save the killed player only if he/she has more than 0
                         * points
                         */
                        if (player.getScore() > 0L) {
                            highScores.addScore(new HighScore(player.getScore(), player.getName()));
                            highScores.KeepTopThreePlayersInHighScoreTable();
                        }

                        highScores.addScore(new HighScore(playerWhoKilledMe.getScore(), playerWhoKilledMe.getName()));

                        player.kill();
                    } else {
                        eventSender.sendItemHitNotification(player.getSpaceShip());
                    }
                    bulletPool.remove(actualBullet);
                }
            }
        }
    }

    private void updatePlayerSpeed(PlayerData player) {
        double canvasHalfWidth = player.getCanvas().getHalfWidth();
        double canvasHalfHeight = player.getCanvas().getHalfHeight();
        double horizontalDistanceFromMidPoint = Math.abs(player.getMouseX() - canvasHalfWidth);
        double verticalDistanceFromMidPoint = Math.abs(player.getMouseY() - canvasHalfHeight);
        double actualDistanceFromScreenMidpoint = Math
                .sqrt(horizontalDistanceFromMidPoint * horizontalDistanceFromMidPoint
                        + verticalDistanceFromMidPoint * verticalDistanceFromMidPoint)
                / 2.0d;

        double maxDistance = Math.sqrt(canvasHalfWidth * canvasHalfWidth + canvasHalfHeight * canvasHalfHeight) / 2.0d;

        double limitation = (actualDistanceFromScreenMidpoint / maxDistance);

        if (limitation < 0.2d) {
            limitation = 0.2d;
        }

        player.setSpeed(player.getMaxSpeed() * limitation);
    }

    private void updateShipAngles(PlayerData player) throws InterruptedException {
        double angle = calculateAngleAndFilterIt(player, (double) player.getCanvas().getHalfWidth(),
                (double) player.getCanvas().getHalfHeight());
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
                checkBulletHits(player);
                checkIfPlayerGetsAnItem(player);
                player.decreaseInvulnerabilityCounter(1L);
                player.getWeapon().decreaseRateOfFireCooldownValue(1L);
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
        CopyOnWriteArrayList<SpawnableItem> items = (CopyOnWriteArrayList<SpawnableItem>) itemPool
                .getAllOnScreen(player.getId());

        Iterator<SpawnableItem> itemIterator = items.iterator();

        while (itemIterator.hasNext()) {
            SpawnableItem actualItem = itemIterator.next();
            boolean areaCheck = Math.abs(actualItem.getX() - player.getX()) < 10.0d
                    && Math.abs(actualItem.getY() - player.getY()) < 10.0d;

            if (areaCheck) {
                actualItem.applyEffect(player);
                itemPool.remove(actualItem);
            }
        }
    }

    private void updatePlayerCollisions(PlayerData player1) {
        for (Long j : playerPool.getAll()) {
            PlayerData player2 = playerPool.get(j);
            if (player2.isSpawned() && player1.getId() != player2.getId()
                    && Math.abs(player1.getX() - player2.getX()) <= 15
                    && Math.abs(player1.getY() - player2.getY()) <= 15) {
                player1.setShieldProtection(0L);
                if (player1.decreaseHp(Physics.COLLISION_STRENGTH) < 0L) {
                    eventSender.sendItemDestroyedNotification(player1.getSpaceShip());
                    player1.kill();

                }
                player2.setShieldProtection(0L);
                if (player2.decreaseHp(Physics.COLLISION_STRENGTH) < 1L) {
                    eventSender.sendItemDestroyedNotification(player2.getSpaceShip());
                    player2.kill();
                }
            }
        }
    }

    /**
     * Updates a given players coordinate.
     */
    private void updatePlayerCoordinates(PlayerData player) {
        player.setCoordinate(coordinateHandler.calculateItemCoordinates(player.getSpaceShip(), player.getSpeed()));
    }
}
