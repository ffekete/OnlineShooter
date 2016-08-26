package game.datatype.ship;

import java.awt.geom.Point2D;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;

public class Deltawing extends ShipParent {

    public Deltawing() {
        super.setCoordinate(new Point2D.Double(0, 0));
        super.setShipConfig(ShipConfig.DELTAWING);
        this.resetSpeed();
        this.resetManeuverability();
        this.resetHp();
        this.setMaxHp(ShipConfig.DELTAWING.getMaxHP());

    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.DELTAWING.getMaxHP())
            hpToSet = ShipConfig.DELTAWING.getMaxHP();
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.DELTAWING.getMaxHP());
    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.DELTAWING.getInitManeuverability());
    }

    @Override
    public void resetSpeed() {
        super.setSpeed(ShipConfig.DELTAWING.getInitMaxSpeed());
        super.setMaxSpeed(ShipConfig.DELTAWING.getInitMaxSpeed());
    }

    @Override
    public void initWeapons() {
        super.initWeapons();
        super.addWeapon(WeaponFactory.createWeapon(ShipConfig.DELTAWING.getInitWeapon()));
        super.selectWeapon(0);
    }
}