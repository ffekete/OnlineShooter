package game.datatype.ship;

import java.awt.geom.Point2D;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;

public class Quicksilver extends ShipParent {

    public Quicksilver() {
        super.setCoordinate(new Point2D.Double(0, 0));
        super.setShipConfig(ShipConfig.QUICKSILVER);
        this.resetSpeed();
        this.resetManeuverability();
        this.resetHp();
        this.setMaxHp(ShipConfig.QUICKSILVER.getMaxHP());
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.QUICKSILVER.getMaxHP())
            hpToSet = ShipConfig.QUICKSILVER.getMaxHP();
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.QUICKSILVER.getMaxHP());
    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.QUICKSILVER.getInitManeuverability());
    }

    @Override
    public void resetSpeed() {
        super.setSpeed(ShipConfig.QUICKSILVER.getInitMaxSpeed());
        super.setMaxSpeed(ShipConfig.QUICKSILVER.getInitMaxSpeed());
    }

    @Override
    public void initWeaponsAndAmmo() {
    	super.initWeaponsAndAmmo();
        super.addWeapon(WeaponFactory.createWeapon(ShipConfig.QUICKSILVER.getInitWeapon()));
        super.selectWeapon(0);
    }
}
