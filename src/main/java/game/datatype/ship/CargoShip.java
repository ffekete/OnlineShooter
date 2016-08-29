package game.datatype.ship;

import java.awt.geom.Point2D;
import java.util.List;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;
import game.interfaces.SpawnableItem;

public class CargoShip extends ShipParent {

    public CargoShip(List<SpawnableItem> carriage) {
        super.setCoordinate(new Point2D.Double(0, 0));
        super.setShipConfig(ShipConfig.CARGOSHIP);
        super.setMaxCargoCapacity(ShipConfig.CARGOSHIP.getCargoCapacity());
        super.setCarriage(carriage);
        this.resetSpeed();
        this.resetManeuverability();
        this.resetHp();
        this.setMaxHp(ShipConfig.CARGOSHIP.getMaxHP());
        this.setHitRadius(getShipConfig().getHitRadius());
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.CARGOSHIP.getMaxHP())
            hpToSet = ShipConfig.CARGOSHIP.getMaxHP();
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.CARGOSHIP.getMaxHP());
    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.CARGOSHIP.getInitManeuverability());
    }

    @Override
    public void resetSpeed() {
        super.setSpeed(ShipConfig.CARGOSHIP.getInitMaxSpeed());
        super.setMaxSpeed(ShipConfig.CARGOSHIP.getInitMaxSpeed());
    }

    @Override
    public void initWeaponsAndAmmo() {
    	super.initWeaponsAndAmmo();
        super.addWeapon(WeaponFactory.createWeapon(ShipConfig.CARGOSHIP.getInitWeapon()));
        super.selectWeapon(0);
    }
}
