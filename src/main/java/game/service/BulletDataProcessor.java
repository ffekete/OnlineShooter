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

    @Autowired
    private CoordinateHandler coordinateHandler;

    private void updateBulletCoordinates(Bullet bullet) {
        bullet.setLocation(coordinateHandler.calculateItemCoordinates(bullet, GameConfig.BULLET_INITIAL_SPEED));
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
