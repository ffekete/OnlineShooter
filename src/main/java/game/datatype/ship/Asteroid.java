package game.datatype.ship;

import java.awt.geom.Point2D;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;
import game.util.RandomGenerator;

public class Asteroid extends ShipParent {
    private double randomSpeed;

    public Asteroid() {
        super.setCoordinate(new Point2D.Double(0, 0));
        super.setShipConfig(ShipConfig.ASTEROID);
        this.resetSpeed();
        this.resetManeuverability();
        this.resetHp();
        this.setMaxHp(ShipConfig.ASTEROID.getMaxHP());
        this.setHitRadius(getShipConfig().getHitRadius());
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.ASTEROID.getMaxHP())
            hpToSet = ShipConfig.ASTEROID.getMaxHP();
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.ASTEROID.getMaxHP());
    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.ASTEROID.getInitManeuverability());
    }

    @Override
    public void resetSpeed() {
        randomSpeed = this.generateRandomSpeed();
        super.setSpeed(randomSpeed);
        super.setMaxSpeed(randomSpeed);
    }

    @Override
    public void initWeaponsAndAmmo() {
        super.initWeaponsAndAmmo();
        super.addWeapon(WeaponFactory.createWeapon(ShipConfig.ASTEROID.getInitWeapon()));
        super.selectWeapon(0);
    }

    private double generateRandomSpeed() {
        return RandomGenerator.getRandomInRange(ShipConfig.ASTEROID.getInitMinSpeed(),
                ShipConfig.ASTEROID.getInitMaxSpeed());
    }
}
