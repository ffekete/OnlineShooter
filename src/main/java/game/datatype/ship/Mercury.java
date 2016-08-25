package game.datatype.ship;

import java.awt.geom.Point2D;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;

public class Mercury extends ShipParent {

    public Mercury() {
        super.setCoordinate(new Point2D.Double(0, 0));
        this.setHp(ShipConfig.MERCURY_MAX_HP);
        this.setMaxHp(ShipConfig.MERCURY_MAX_HP);
        super.setSpeed(ShipConfig.MERCURY_INIT_SPEED);
        super.setMaxSpeed(ShipConfig.MERCURY_INIT_SPEED);
        super.setManeuverability(ShipConfig.MERCURY_INIT_MANEUVERABILITY);
        super.setShipType(ShipConfig.SHIP_TYPE_MERCURY);
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.MERCURY_MAX_HP)
            hpToSet = ShipConfig.MERCURY_MAX_HP;
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.MERCURY_MAX_HP);
    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.MERCURY_INIT_MANEUVERABILITY);
    }

    @Override
    public void resetSpeed() {
        super.setSpeed(ShipConfig.MERCURY_INIT_SPEED);
        super.setMaxSpeed(ShipConfig.MERCURY_INIT_SPEED);
    }

    @Override
    public void initWeapons() {
    	super.initWeapons();
        super.addWeapon(WeaponFactory.createWeapon(ShipConfig.MERCURY_INIT_WEAPON));
        super.selectWeapon(0);
    }

}
