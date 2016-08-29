package game.datatype.ship;

import java.awt.geom.Point2D;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;

public class Interceptor extends ShipParent {

    public Interceptor() {
        super.setCoordinate(new Point2D.Double(0, 0));
        super.setShipConfig(ShipConfig.INTERCEPTOR);
        this.resetSpeed();
        this.resetManeuverability();
        this.resetHp();
        this.setMaxHp(ShipConfig.INTERCEPTOR.getMaxHP());
        this.setHitRadius(getShipConfig().getHitRadius());
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.INTERCEPTOR.getMaxHP())
            hpToSet = ShipConfig.INTERCEPTOR.getMaxHP();

        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.INTERCEPTOR.getMaxHP());

    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.INTERCEPTOR.getInitManeuverability());
    }

    @Override
    public void resetSpeed() {
        super.setSpeed(ShipConfig.INTERCEPTOR.getInitMaxSpeed());
        super.setMaxSpeed(ShipConfig.INTERCEPTOR.getInitMaxSpeed());
    }

    @Override
    public void initWeaponsAndAmmo() {
    	super.initWeaponsAndAmmo();
        super.addWeapon(WeaponFactory.createWeapon(ShipConfig.INTERCEPTOR.getInitWeapon()));
        super.selectWeapon(0);
    }
}
