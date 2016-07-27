package service;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.CanvasConstants;
import config.GameConfig;
import datahandler.BulletsPool;
import datahandler.PlayerPool;
import model.BulletData;
import model.PlayerData;

/** Basic class to calculate player related values e.g. ship angles,... */
@Component
public class PlayerDataProcessor {
	@Autowired
	PlayerPool playerPool;

	@Autowired
	BulletsPool bulletPool;

	/** Calculates an angle using two points. */
	private double calculateAngle(double targetX, double targetY, double baseX, double baseY) {
		double angle = Math.toDegrees(Math.atan2(targetY - baseY, targetX - baseX));

		if (angle < 0) {
			angle += 360;
		}

		return angle;
	}

	private void checkBulletHits(PlayerData player) {
		CopyOnWriteArrayList<BulletData> bullets = (CopyOnWriteArrayList<BulletData>) bulletPool
				.getAllBulletsOnScreen(player.getId());

		Iterator<BulletData> bulletIterator = bullets.iterator();

		while (bulletIterator.hasNext()) {
			BulletData actualBullet = bulletIterator.next();

			boolean invulnerabilityCheck = player.getInvulnerabilityCounter() < 1L; // true, if player is no invulnerable
			boolean playerIdCheck = actualBullet.getPlayerId() != player.getId(); 
			boolean areaCheck = Math.abs(actualBullet.getX() - player.getX()) < 10.0d
					&& Math.abs(actualBullet.getY() - player.getY()) < 10.0d;
			
			if (invulnerabilityCheck && playerIdCheck && areaCheck) {
				bulletPool.getBulletPool().remove(actualBullet);
				player.decreaseHp(1L);
				if (player.getHp() < 1L) {
					player.kill();
				}
			}
		}
	}

	private void updateShipAngles(PlayerData player) throws InterruptedException {
		player.setAngle(calculateAngle(player.getMouseX(), player.getMouseY(),
				(double) CanvasConstants.CANVAS_HALF_WIDTH, (double) CanvasConstants.CANVAS_HALF_HEIGHT));
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
			updateShipAngles(player);
			updatePlayerCoordinates(player);
			checkBulletHits(player);
			player.decreaseInvulnerabilityCounter(1L);
			player.decreaseRateOfFireCooldownValue(1L);
		}

	}

	/** Updates a given players coordinate. */
	private void updatePlayerCoordinates(PlayerData player) {
		double resultx;
		double resulty;
		double angle = player.getAngle() * Math.PI / 180.0d;

		resultx = player.getX() + GameConfig.SHIP_INITIAL_SPEED * Math.cos(angle);
		resulty = player.getY() + GameConfig.SHIP_INITIAL_SPEED * Math.sin(angle);

		player.setX(resultx);
		player.setY(resulty);
	}
}
