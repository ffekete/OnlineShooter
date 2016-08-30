package game.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.interfaces.Ammo;
import game.interfaces.AmmoDataProcessorInterface;
import game.interfaces.AmmoPoolList;

@Component
public class AmmoDataProcessor implements AmmoDataProcessorInterface {

    @Autowired
    private AmmoPoolList<Ammo> ammoPool;

    @Autowired
    private CoordinateHandler coordinateHandler;

    private void updateAmmoCoordinates(Ammo ammo) {
    	ammo.setCoordinate(coordinateHandler.calculateItemCoordinates(ammo, ammo.getSpeed(), null));
    }

    private void processAgeCounter(Ammo ammo) {
    	ammo.increaseAge();
        if (ammo.isAgeCounterExpired()) {
            boolean result = ammoPool.remove(ammo);
            if (result == false) {
                System.out.println("Bullet cannot be removed." + ammo);
            }
        }
    }

    @Override
    public void updateAmmoData() {
        Iterator<Ammo> bit = ammoPool.getIterator();

        while (bit.hasNext()) {
            Ammo ammo = bit.next();
            ammo.effect();
            updateAmmoCoordinates(ammo);
            processAgeCounter(ammo);
        }
    }
}
