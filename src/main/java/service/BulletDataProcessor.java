package service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.GameConfig;
import datahandler.BulletsPool;
import model.BulletData;

@Component
public class BulletDataProcessor {
	@Autowired
	BulletsPool bulletPool;
	
	public void updateBulletCoordinates(){
		Iterator<BulletData> bit = bulletPool.getBulletPool().iterator();
		
		while(bit.hasNext()){
			BulletData bullet = bit.next();
			double resultx;
			double resulty;
			double angle = bullet.getAngle() * Math.PI / 180;
			
			resultx = bullet.getX() + GameConfig.BULLET_INITIAL_SPEED * Math.cos(angle);
			resulty = bullet.getY() + GameConfig.BULLET_INITIAL_SPEED * Math.sin(angle);
			 	
			
			bullet.setX((long)resultx);
			bullet.setY((long)resulty);
			//System.out.println("Updated: " + resultx + " " + resulty + " " + bullet.getAngle());
		}
	}
}
