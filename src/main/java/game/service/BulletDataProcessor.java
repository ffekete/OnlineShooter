package game.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.GameConfig;
import game.datahandler.BulletPool;
import game.interfaces.Bullet;
import game.interfaces.BulletDataProcessorInterface;

@Component
public class BulletDataProcessor implements BulletDataProcessorInterface {
    
    @Autowired
    private BulletPool bulletPool;
    
    private void updateBulletCoordinates(Bullet bullet){
        double resultx;
        double resulty;
        
        bullet.effect();
        
        double angle = bullet.getAngle() * Math.PI / 180.0d;
        
        resultx = (double)bullet.getX() + GameConfig.BULLET_INITIAL_SPEED * Math.cos(angle);
        resulty = (double)bullet.getY() + GameConfig.BULLET_INITIAL_SPEED * Math.sin(angle);
        
        if(resultx > GameConfig.STAGE_POS_LIMIT_X) resultx = GameConfig.STAGE_NEG_LIMIT_X + (resultx - GameConfig.STAGE_POS_LIMIT_X);
        if(resultx < GameConfig.STAGE_NEG_LIMIT_X) resultx = GameConfig.STAGE_POS_LIMIT_X + (resultx + GameConfig.STAGE_POS_LIMIT_X);
        
        if(resulty > GameConfig.STAGE_POS_LIMIT_Y) resulty = GameConfig.STAGE_NEG_LIMIT_Y + (resulty - GameConfig.STAGE_POS_LIMIT_Y);
        if(resulty < GameConfig.STAGE_NEG_LIMIT_Y) resulty = GameConfig.STAGE_POS_LIMIT_Y + (resulty + GameConfig.STAGE_NEG_LIMIT_Y);
        
        bullet.setX(resultx);
        bullet.setY(resulty);
    }
    
    private void processAgeCounter(Bullet bullet){
        bullet.increaseAge();
        if(bullet.isAgeCounterExpired()){
            boolean result = bulletPool.getBulletPool().remove(bullet);
            if(result == false)
            {
                System.out.println("Bullet cannot be removed." + bullet);
            }
        }
    }
    
    /* (non-Javadoc)
	 * @see game.service.BulletDataProcessorInterface#updateBulletData()
	 */
    @Override
	public void updateBulletData(){
        Iterator<Bullet> bit = bulletPool.getBulletPool().iterator();
        
        while(bit.hasNext()){
            Bullet bullet = bit.next();
            updateBulletCoordinates(bullet);
            processAgeCounter(bullet);
        }
    }
}
