package datahandler;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.BulletData;
import model.PlayerData;

@Component
public class BulletPool {
	
	@Autowired
	PlayerPool playerPool;
	
	private List<BulletData> bulletPool;
	
	public BulletPool(){
		bulletPool = new CopyOnWriteArrayList<BulletData>();
	}

	public synchronized List<BulletData> getBulletPool() {
		return bulletPool;
	}

	public synchronized List<BulletData> getAllBulletsOnScreen(Long playerId){
		CopyOnWriteArrayList<BulletData> allBulletsOnScreen = new CopyOnWriteArrayList<BulletData>();
		
		if(playerId != null){
			Iterator<BulletData> bit = bulletPool.iterator();
			
			while(bit.hasNext()){
				PlayerData player = playerPool.getPlayerById(playerId);
				
				if(player != null){
					BulletData actualBullet = bit.next();
					if((Math.abs(actualBullet.getX() - player.getX()) <= player.getCanvas().getHalfWidth()) &&
							(Math.abs(actualBullet.getY() - player.getY()) <= player.getCanvas().getHalfHeight())){
						allBulletsOnScreen.add(actualBullet);
					}
				}
			}
		}
		
		return allBulletsOnScreen;
	}
	
	public synchronized void addBullet(Long playerId){
		if(playerId != null){
			PlayerData player = playerPool.getPlayerById(playerId);
			if(player != null && player.getWeapon().canShoot()){
				List<BulletData> bulletsToCreate = player.getWeapon().createBullet(player);
				
				Iterator<BulletData> it = bulletsToCreate.iterator();
				while(it.hasNext()){
					bulletPool.add(it.next());
				}
				player.getWeapon().startShootingRateCooldownEffect();
				player.getWeapon().decreaseAmmo(1L);
				if(player.getWeapon().getAmmo() == 0L)
					player.initWeapon();
			}
		}
	}
	
}
