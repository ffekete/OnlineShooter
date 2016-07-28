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
	private BulletsPool bulletPool;
	
	private void updateBulletCoordinates(BulletData bullet){
		double resultx;
		double resulty;
		double angle = bullet.getAngle() * Math.PI / 180.0d;
		
		resultx = (double)bullet.getX() + GameConfig.BULLET_INITIAL_SPEED * Math.cos(angle);
		resulty = (double)bullet.getY() + GameConfig.BULLET_INITIAL_SPEED * Math.sin(angle);
		
		if(resultx > GameConfig.STAGE_POS_LIMIT_X) resultx = GameConfig.STAGE_NEG_LIMIT_X;
		if(resultx < GameConfig.STAGE_NEG_LIMIT_X) resultx = GameConfig.STAGE_POS_LIMIT_X;
		
		if(resulty > GameConfig.STAGE_POS_LIMIT_Y) resulty = GameConfig.STAGE_NEG_LIMIT_Y;
		if(resulty < GameConfig.STAGE_NEG_LIMIT_Y) resulty = GameConfig.STAGE_POS_LIMIT_Y;
		
		bullet.setX(resultx);
		bullet.setY(resulty);
	}
	
	private void processAgeCounter(BulletData bullet){
		bullet.increaseAge();
		if(bullet.getAge() >= GameConfig.BULLET_MAX_AGE){
			boolean result = bulletPool.getBulletPool().remove(bullet);
			if(result == false)
			{
				System.out.println("Bullet cannot be removed." + bullet);
			}
		}
	}
	
	public void updateBulletData(){
		Iterator<BulletData> bit = bulletPool.getBulletPool().iterator();
		
		while(bit.hasNext()){
			BulletData bullet = bit.next();
			updateBulletCoordinates(bullet);
			processAgeCounter(bullet);
		}
	}
}
