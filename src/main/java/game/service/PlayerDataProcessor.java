package game.service;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.GameConfig;
import game.config.Physics;
import game.controller.EventSender;
import game.datahandler.BulletPool;
import game.datahandler.HighScoreTable;
import game.datahandler.ItemPool;
import game.datahandler.PlayerPool;
import game.interfaces.Bullet;
import game.interfaces.SpawnableItem;
import game.model.HighScore;
import game.model.PlayerData;
import game.scheduler.TaskScheduler;

/** Basic class to calculate player related values e.g. ship angles,... */
@Component
public class PlayerDataProcessor {
    @Autowired
    PlayerPool playerPool;

    @Autowired
    BulletPool bulletPool;

    @Autowired
    ItemPool itemPool;

    @Autowired
    HighScoreTable highScores;

    @Autowired
    TaskScheduler taskScheduler;
    
    @Autowired
    EventSender eventSender;

    /** Calculates an angle using two points. */
    private double calculateAngleAndFilterIt(PlayerData player, double baseX, double baseY) {
        double targetX = player.getMouseX();
        double targetY = player.getMouseY();
        double angle = Math.toDegrees(Math.atan2(targetY - baseY, targetX - baseX));
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
        CopyOnWriteArrayList<Bullet> bullets = (CopyOnWriteArrayList<Bullet>) bulletPool.getAllBulletsOnScreen(player.getId());

        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet actualBullet = bulletIterator.next();

            boolean invulnerabilityCheck = player.getInvulnerabilityCounter() < 1L; // true, if player is not invulnerable
            boolean playerIdCheck = actualBullet.getPlayerId() != player.getId();
            
            if(playerIdCheck){
                boolean areaCheck = actualBullet.hits(player);
    
                if (invulnerabilityCheck && areaCheck) {
                    PlayerData playerToSave = new PlayerData(player);
                    
                    actualBullet.hitDetected(playerToSave, eventSender);
                    
                    long hpRemaining = player.decreaseHp(actualBullet.getDamage());
                    if (hpRemaining < 1L) {
                        
                        /* populate death event to client side */
                        eventSender.sendItemDestroyedNotification(playerToSave);
                        
                        PlayerData playerWhoKilledMe = playerPool.getPlayerById(actualBullet.getPlayerId());
                        playerWhoKilledMe.increaseScore(GameConfig.PLAYER_SCORE_VALUE);
    
                        /*
                         * Save the killed player only if he/she has more than 0
                         * points
                         */
                        if (playerToSave.getScore() > 0L) {
                            highScores.addScore(new HighScore(playerToSave.getScore(), playerToSave.getName()));
                            highScores.KeepTopThreePlayersInHighScoreTable();
                        }
    
                        highScores.addScore(new HighScore(playerWhoKilledMe.getScore(), playerWhoKilledMe.getName()));
                    }
                    else
                    {
                        eventSender.sendItemHitNotification(player);
                    }
                    bulletPool.getBulletPool().remove(actualBullet);
    
                }
            }
        }
    }

    private void updatePlayerSpeed(PlayerData player) {
        double a = Math.abs(player.getMouseX() - player.getCanvas().getHalfWidth());
        double b = Math.abs(player.getMouseY() - player.getCanvas().getHalfHeight());
        double actualDistanceFromScreenMidpoint = Math.sqrt(a * a + b * b) / 2.0d;
        
        double canvasHalfWidth = player.getCanvas().getHalfWidth();
        double canvasHalfHeight = player.getCanvas().getHalfHeight();
        
        double maxDistance = Math.sqrt(canvasHalfWidth * canvasHalfWidth + canvasHalfHeight * canvasHalfHeight) / 2.0d;

        double limitation = (actualDistanceFromScreenMidpoint / maxDistance);

        if(limitation < 0.2d) limitation = 0.2d;
        
        player.setSpeed(player.getMaxSpeed() * limitation);
    }

    private void updateShipAngles(PlayerData player) throws InterruptedException {
        double angle = calculateAngleAndFilterIt(player, (double) player.getCanvas().getHalfWidth(),
                (double) player.getCanvas().getHalfHeight());
        player.setPreviousAngle(player.getShipAngle());
        player.setShipAngle(angle);
    }

    /**
     * Main loop function
     * 
     * @throws InterruptedException
     */
    public void updatePlayerData() throws InterruptedException {
        Iterator<Long> shipIds = playerPool.getPool().keySet().iterator();

        while (shipIds.hasNext()) {
            Long playerId = shipIds.next();
            PlayerData player = playerPool.getPlayerById(playerId);
            if (player.isSpawned()) {
                updateShipAngles(player);
                updatePlayerCoordinates(player);
                updatePlayerSpeed(player);
                updatePlayerCollisions(player);
                checkBulletHits(player);
                checkIfPlayerGetsAnItem(player);
                player.decreaseInvulnerabilityCounter(1L);
                player.getWeapon().decreaseRateOfFireCooldownValue(1L);
                if (taskScheduler.getTimer() == 0) { // increases shield value in every 5th loop
                    player.increaseShieldPower();
                }
            }
            else
            {
                player.decreasePlayerRespawnTime();
            }
        }

    }

    public void checkIfPlayerGetsAnItem(PlayerData player) {
        CopyOnWriteArrayList<SpawnableItem> items = (CopyOnWriteArrayList<SpawnableItem>) itemPool
                .getAllItemsOnScreen(player);

        Iterator<SpawnableItem> itemIterator = items.iterator();

        while (itemIterator.hasNext()) {
            SpawnableItem actualItem = itemIterator.next();
            boolean areaCheck = Math.abs(actualItem.getX() - player.getX()) < 10.0d
                    && Math.abs(actualItem.getY() - player.getY()) < 10.0d;

            if (areaCheck) {
                actualItem.applyEffect(player);
                itemPool.removeItem(actualItem);
            }
        }
    }

    private void updatePlayerCollisions(PlayerData player1) {
        for (Long j : playerPool.getPool().keySet()) {
            PlayerData player2 = playerPool.getPlayerById(j);
            if (player2.isSpawned() &&
                player1.getId() != player2.getId() && 
                Math.abs(player1.getX() - player2.getX()) <= 15
                && Math.abs(player1.getY() - player2.getY()) <= 15) {
                    
                    PlayerData player1ToSave = new PlayerData(player1);
                    PlayerData player2ToSave = new PlayerData(player2);
                    
                    player1.getShield().setProtection(0L);
                    if(player1.decreaseHp(Physics.COLLISION_STRENGTH) < 0L){
                        eventSender.sendItemDestroyedNotification(player1ToSave);
                    }
                    player2.getShield().setProtection(0L);
                    if(player2.decreaseHp(Physics.COLLISION_STRENGTH) < 1L){
                        eventSender.sendItemDestroyedNotification(player2ToSave);
                    }
            }
        }
    }

    /** Updates a given players coordinate. */
    private void updatePlayerCoordinates(PlayerData player) {
        double resultx;
        double resulty;
        double angle = player.getShipAngle() * Math.PI / 180.0d;

        resultx = player.getX() + player.getSpeed() * Math.cos(angle);
        resulty = player.getY() + player.getSpeed() * Math.sin(angle);

        if (resultx > GameConfig.STAGE_POS_LIMIT_X)
            resultx = GameConfig.STAGE_NEG_LIMIT_X;
        if (resultx < GameConfig.STAGE_NEG_LIMIT_X)
            resultx = GameConfig.STAGE_POS_LIMIT_X;

        if (resulty > GameConfig.STAGE_POS_LIMIT_Y)
            resulty = GameConfig.STAGE_NEG_LIMIT_Y;
        if (resulty < GameConfig.STAGE_NEG_LIMIT_Y)
            resulty = GameConfig.STAGE_POS_LIMIT_Y;

        player.setX(resultx);
        player.setY(resulty);
    }
}
