package datahandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.CanvasConstants;
import model.BulletData;
import model.PlayerData;

@Component
public class BulletsPool {
	
	@Autowired
	PlayerPool playerPool;
	
	private List<BulletData> bulletPool;
	
	public BulletsPool(){
		bulletPool = new CopyOnWriteArrayList<BulletData>();
	}
		
	public synchronized List<BulletData> getBulletPool() {
		return bulletPool;
	}

	public synchronized void setBulletPool(List<BulletData> bulletPool) {
		this.bulletPool = bulletPool;
	}

	public synchronized List<BulletData> getAllBulletsOnScreen(Long playerId){
		ArrayList<BulletData> allBulletsOnScreen = new ArrayList<BulletData>();
		Iterator<BulletData> bit = bulletPool.iterator();
		
		while(bit.hasNext()){
			PlayerData player = playerPool.getPlayerById(playerId);
			
			if(player != null){
				BulletData actualBullet = bit.next();
				if((Math.abs(actualBullet.getX() - player.getX()) <= CanvasConstants.CANVAS_HALF_WIDTH) &&
						(Math.abs(actualBullet.getY() - player.getY()) <= CanvasConstants.CANVAS_HALF_HEIGHT)){
					allBulletsOnScreen.add(actualBullet);
				}
			}
		}
		
		return allBulletsOnScreen;
	}
	
	public synchronized void addBullet(Long playerId){
		PlayerData player = playerPool.getPlayerById(playerId);
		bulletPool.add(new BulletData(player.getX(), player.getY(), player.getShipAngle()));
	}
	
}
