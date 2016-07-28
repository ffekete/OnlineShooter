package service;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.CanvasConstants;
import config.GameConfig;
import config.Physics;
import datahandler.BulletsPool;
import datahandler.ItemPool;
import datahandler.PlayerPool;
import interfaces.SpawnableItem;
import model.BulletData;
import model.PlayerData;

/** Basic class to calculate player related values e.g. ship angles,... */
@Component
public class PlayerDataProcessor {
	@Autowired
	PlayerPool playerPool;

	@Autowired
	BulletsPool bulletPool;

	@Autowired
	ItemPool itemPool;

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

		if(previousAngle - angle > 180.0){
			angle = 360.0 + angle; 
		}
		
		else if(angle - previousAngle > 180.0){
			angle = angle - 360.0; 
		}
		
     	smoothedValue += (angle - smoothedValue) / player.getManeuverability();
     	
     	if(smoothedValue > 360.0d)
     	{
     		smoothedValue -=360.0d;
     	}
     	else if(smoothedValue <0.0d)
     	{
     		smoothedValue +=360.0d;
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
				player.decreaseHp(actualBullet.getDamage());
				bulletPool.getBulletPool().remove(actualBullet);
				
			}
		}
	}

	private void updateShipAngles(PlayerData player) throws InterruptedException {
		double angle = calculateAngleAndFilterIt(player, (double) CanvasConstants.CANVAS_HALF_WIDTH, (double) CanvasConstants.CANVAS_HALF_HEIGHT);
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
			updateShipAngles(player);
			updatePlayerCoordinates(player);
			checkBulletHits(player);
			checkIfPlayerGetsAnItem(player);
			player.decreaseInvulnerabilityCounter(1L);
			player.getWeapon().decreaseRateOfFireCooldownValue(1L);
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
