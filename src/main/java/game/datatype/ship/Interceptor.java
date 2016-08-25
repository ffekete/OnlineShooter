package game.datatype.ship;

import java.awt.geom.Point2D;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;

public class Interceptor extends ShipParent {

    public Interceptor() {
        super.setCoordinate(new Point2D.Double(0, 0));
        this.setHp(ShipConfig.INTERCEPTOR_MAX_HP);
        this.setMaxHp(ShipConfig.INTERCEPTOR_MAX_HP);
        super.setSpeed(ShipConfig.INTERCEPTOR_INIT_SPEED);
        super.setMaxSpeed(ShipConfig.INTERCEPTOR_INIT_SPEED);
        super.setManeuverability(ShipConfig.INTERCEPTOR_INIT_MANEUVERABILITY);
        super.setShipType(ShipConfig.SHIP_TYPE_INTERCEPTOR);
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.INTERCEPTOR_MAX_HP)
            hpToSet = ShipConfig.INTERCEPTOR_MAX_HP;

        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.INTERCEPTOR_MAX_HP);

    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.INTERCEPTOR_INIT_MANEUVERABILITY);
    }

    @Override
    public void resetSpeed() {
        super.setSpeed(ShipConfig.INTERCEPTOR_INIT_SPEED);
        super.setMaxSpeed(ShipConfig.INTERCEPTOR_INIT_SPEED);
    }

    @Override
    public void initWeapons() {
        super.addWeapon(WeaponFactory.createWeapon(ShipConfig.INTERCEPTOR_INIT_WEAPON));
        super.selectWeapon(0);
    }
}
