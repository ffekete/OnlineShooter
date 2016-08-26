package game.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.constant.GameConfig;
import game.interfaces.Bullet;
import game.interfaces.BulletDataProcessorInterface;
import game.interfaces.BulletPoolList;

@Component
public class BulletDataProcessor implements BulletDataProcessorInterface {

    @Autowired
    private BulletPoolList<Bullet> bulletPool;

    @Autowired
    private CoordinateHandler coordinateHandler;

    private void updateBulletCoordinates(Bullet bullet) {
        bullet.setCoordinate(coordinateHandler.calculateItemCoordinates(bullet, GameConfig.BULLET_INITIAL_SPEED, null));
    }

    private void processAgeCounter(Bullet bullet) {
        bullet.increaseAge();
        if (bullet.isAgeCounterExpired()) {
            boolean result = bulletPool.remove(bullet);
            if (result == false) {
                System.out.println("Bullet cannot be removed." + bullet);
            }
        }
    }

    @Override
    public void updateBulletData() {
        Iterator<Bullet> bit = bulletPool.getIterator();

        while (bit.hasNext()) {
            Bullet bullet = bit.next();
            bullet.effect();
            updateBulletCoordinates(bullet);
            processAgeCounter(bullet);
        }
    }
}
