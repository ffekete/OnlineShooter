package game.datatype.ship;

import java.awt.geom.Point2D;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;

public class Mercury extends ShipParent {

    public Mercury() {
        super.setCoordinate(new Point2D.Double(0, 0));
        super.setShipConfig(ShipConfig.MERCURY);
        this.resetSpeed();
        this.resetManeuverability();
        this.resetHp();
        this.setMaxHp(ShipConfig.MERCURY.getMaxHP());
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.MERCURY.getMaxHP())
            hpToSet = ShipConfig.MERCURY.getMaxHP();
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.MERCURY.getMaxHP());
    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.MERCURY.getInitManeuverability());
    }

    @Override
    public void resetSpeed() {
        super.setSpeed(ShipConfig.MERCURY.getInitMaxSpeed());
        super.setMaxSpeed(ShipConfig.MERCURY.getInitMaxSpeed());
    }

    @Override
    public void initWeaponsAndAmmo() {
    	super.initWeaponsAndAmmo();
        super.addWeapon(WeaponFactory.createWeapon(ShipConfig.MERCURY.getInitWeapon()));
        super.selectWeapon(0);
    }

}
