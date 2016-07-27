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

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	public void checkAllBulletsHitEffect(){
		for(Long id : playerPool.getPool().keySet()){
			PlayerData player = playerPool.getPlayerById(id);
			checkBulletHits(player);
		}
	}
	
	public void checkBulletHits(PlayerData player){
		CopyOnWriteArrayList<BulletData> bullets = (CopyOnWriteArrayList<BulletData>) bulletPool.getAllBulletsOnScreen(player.getId());
		
		Iterator<BulletData> bulletIterator = bullets.iterator();
		
		while(bulletIterator.hasNext()){
			BulletData actualBullet = bulletIterator.next();
			
			if(actualBullet.getPlayerId() != player.getId() && Math.abs(actualBullet.getX() - player.getX()) < 10.0d && Math.abs(actualBullet.getY() - player.getY()) < 10.0d){
				bulletPool.getBulletPool().remove(actualBullet);
				player.setHp(player.getHp() - 1);
				if(player.getHp() < 1){
					playerPool.removePlayer(player);
				}
			}
		}
	}
	
	public void updateShipAngles() throws InterruptedException{
		
		if(playerPool != null){
			Iterator<Long> shipIds = playerPool.getPool().keySet().iterator();
			
			while(shipIds.hasNext()){
				Long nextShipId = shipIds.next();
				PlayerData nextShip = playerPool.getPlayerById(nextShipId);
				nextShip.setAngle(calculateAngle(nextShip.getMouseX(), nextShip.getMouseY(), (double)CanvasConstants.CANVAS_HALF_WIDTH, (double)CanvasConstants.CANVAS_HALF_HEIGHT));	
			}
		}
	}
	
	public void updatePlayerCoordinates(){
		if(playerPool != null){
			Iterator<Long> shipIds = playerPool.getPool().keySet().iterator();
			
			while(shipIds.hasNext()){
				Long nextShipId = shipIds.next();
				PlayerData nextShip = playerPool.getPlayerById(nextShipId);
				
				double resultx;
				double resulty;
				double angle = nextShip.getAngle() * Math.PI / 180.0d;
				
				resultx = nextShip.getX() + GameConfig.SHIP_INITIAL_SPEED * Math.cos(angle);
				resulty = nextShip.getY() + GameConfig.SHIP_INITIAL_SPEED * Math.sin(angle);
				
				nextShip.setX(resultx);
				nextShip.setY(resulty);
			}
		}
	}
}
