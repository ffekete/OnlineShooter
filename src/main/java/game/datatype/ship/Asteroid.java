package game.datatype.ship;

import java.awt.geom.Point2D;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;
import game.util.RandomGenerator;

public class Asteroid extends ShipParent {
    private double randomSpeed;

    public Asteroid() {
        super.setCoordinate(new Point2D.Double(0, 0));
        this.setHp(ShipConfig.ASTEROID_MAX_HP);
        this.setMaxHp(ShipConfig.ASTEROID_MAX_HP);
        randomSpeed = this.generateRandomSpeed();
        super.setSpeed(randomSpeed);
        super.setMaxSpeed(randomSpeed);
        super.setManeuverability(ShipConfig.ASTEROID_MANEUVERABILITY);
        super.setShipType(ShipConfig.SHIP_TYPE_ASTEROID);
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.ASTEROID_MAX_HP)
            hpToSet = ShipConfig.ASTEROID_MAX_HP;
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.ASTEROID_MAX_HP);
    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.ASTEROID_MANEUVERABILITY);
    }

    @Override
    public void resetSpeed() {
        randomSpeed = this.generateRandomSpeed();
        super.setSpeed(randomSpeed);
        super.setMaxSpeed(randomSpeed);
    }

    @Override
    public void initWeapons() {
        super.initWeapons();
        super.addWeapon(WeaponFactory.createWeapon(ShipConfig.ASTEROID_INIT_WEAPON));
        super.selectWeapon(0);
    }

    private double generateRandomSpeed() {
        return RandomGenerator.getRandomInRange(ShipConfig.ASTEROID_INIT_MIN_SPEED, ShipConfig.ASTEROID_INIT_MAX_SPEED);
    }
}
