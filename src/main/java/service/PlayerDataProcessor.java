package service;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.CanvasConstants;
import config.GameConfig;
import config.Physics;
import datahandler.BulletPool;
import datahandler.HighScoreTable;
import datahandler.ItemPool;
import datahandler.PlayerPool;
import interfaces.SpawnableItem;
import model.BulletData;
import model.HighScore;
import model.PlayerData;
import scheduler.TaskScheduler;

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
		CopyOnWriteArrayList<BulletData> bullets = (CopyOnWriteArrayList<BulletData>) bulletPool
				.getAllBulletsOnScreen(player.getId());

		Iterator<BulletData> bulletIterator = bullets.iterator();

		while (bulletIterator.hasNext()) {
			BulletData actualBullet = bulletIterator.next();

			boolean invulnerabilityCheck = player.getInvulnerabilityCounter() < 1L; // true,
																					// if
																					// player
																					// is
																					// no
																					// invulnerable
			boolean playerIdCheck = actualBullet.getPlayerId() != player.getId();
			boolean areaCheck = Math.abs(actualBullet.getX() - player.getX()) < 10.0d
					&& Math.abs(actualBullet.getY() - player.getY()) < 10.0d;

			if (invulnerabilityCheck && playerIdCheck && areaCheck) {
				PlayerData playerToSave = player;
				long hpRemaining = player.decreaseHp(actualBullet.getDamage());
				if (hpRemaining < 1L) {
					PlayerData playerWhoKilledMe = playerPool.getPlayerById(actualBullet.getPlayerId());
					playerWhoKilledMe.increaseScore(GameConfig.PLAYER_SCORE_VALUE);

					/*
					 * Save the killed player only if he/she has more than 0
					 * points
					 */
					if (playerToSave.getScore() > 0L) {
						highScores.addScore(new HighScore(playerToSave.getScore(), playerToSave.getName()));
					}

					highScores.addScore(new HighScore(playerWhoKilledMe.getScore(), playerWhoKilledMe.getName()));
				}
				bulletPool.getBulletPool().remove(actualBullet);

			}
		}
	}

	private void updatePlayerSpeed(PlayerData player) {
		double a = Math.abs(player.getMouseX() - CanvasConstants.CANVAS_HALF_WIDTH);
		double b = Math.abs(player.getMouseY() - CanvasConstants.CANVAS_HALF_HEIGHT);
		double c = Math.sqrt(a * a + b * b);

		double maxDistance = CanvasConstants.CANVAS_MAX_DISTANCE_FROM_MIDPOINT;

		double limitation = c / maxDistance;

		if (limitation < 0.2d) {
			limitation = 0.5d;
		} else if (limitation < 0.5d) {
			limitation += 0.2d;
		} else if (limitation < 0.8d) {
			limitation += 0.1d;
		}

		player.setSpeed(player.getMaxSpeed() * limitation);
	}

	private void updateShipAngles(PlayerData player) throws InterruptedException {
		double angle = calculateAngleAndFilterIt(player, (double) CanvasConstants.CANVAS_HALF_WIDTH,
				(double) CanvasConstants.CANVAS_HALF_HEIGHT);
		player.setPreviousAngle(player.getAngle());
		player.setAngle(angle);
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
					player1.getShield().setProtection(0L);
					player1.decreaseHp(Physics.COLLISION_STRENGTH);
					player2.getShield().setProtection(0L);
					player2.decreaseHp(Physics.COLLISION_STRENGTH);
			}
		}
	}

	/** Updates a given players coordinate. */
	private void updatePlayerCoordinates(PlayerData player) {
		double resultx;
		double resulty;
		double angle = player.getAngle() * Math.PI / 180.0d;

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
