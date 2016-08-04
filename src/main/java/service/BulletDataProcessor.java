package service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.GameConfig;
import datahandler.BulletPool;
import interfaces.Bullet;

@Component
public class BulletDataProcessor {
	
	@Autowired
	private BulletPool bulletPool;
	
	private void updateBulletCoordinates(Bullet bullet){
		double resultx;
		double resulty;
		
		bullet.effect();
		
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
	
	private void processAgeCounter(Bullet bullet){
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
		Iterator<Bullet> bit = bulletPool.getBulletPool().iterator();
		
		while(bit.hasNext()){
			Bullet bullet = bit.next();
			updateBulletCoordinates(bullet);
			processAgeCounter(bullet);
		}
	}
}
