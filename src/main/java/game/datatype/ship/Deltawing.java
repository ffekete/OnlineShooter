package game.datatype.ship;

import java.awt.geom.Point2D;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;

public class Deltawing extends ShipParent {

    public Deltawing() {
        super.setCoordinate(new Point2D.Double(0, 0));
        this.setHp(ShipConfig.DELTAWING_INIT_HP);
        super.setSpeed(ShipConfig.DELTAWING_INIT_SPEED);
        super.setMaxSpeed(ShipConfig.DELTAWING_INIT_SPEED);
        super.setManeuverability(ShipConfig.DELTAWING_INIT_MANEUVERABILITY);
        super.setShipType(ShipConfig.SHIP_TYPE_DELTAWING);
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.DELTAWING_INIT_HP)
            hpToSet = ShipConfig.DELTAWING_INIT_HP;
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.DELTAWING_INIT_HP);
    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.DELTAWING_INIT_MANEUVERABILITY);
    }

    @Override
    public void resetSpeed() {
        super.setSpeed(ShipConfig.DELTAWING_INIT_SPEED);
        super.setMaxSpeed(ShipConfig.DELTAWING_INIT_SPEED);
    }

    @Override
    public void initWeapon() {
        super.setWeapon(WeaponFactory.createWeapon(ShipConfig.DELTAWING_INIT_WEAPON));

    }
}