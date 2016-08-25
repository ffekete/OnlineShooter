package game.datatype.ship;

import java.awt.geom.Point2D;
import java.util.List;

import factory.WeaponFactory;
import game.config.constant.ShipConfig;
import game.interfaces.SpawnableItem;

public class CargoShip extends ShipParent {

    public CargoShip(List<SpawnableItem> carriage) {
        super.setCoordinate(new Point2D.Double(0, 0));
        this.setHp(ShipConfig.CARGO_SHIP_MAX_HP);
        this.setMaxHp(ShipConfig.CARGO_SHIP_MAX_HP);
        super.setSpeed(ShipConfig.CARGO_SHIP_INIT_SPEED);
        super.setMaxSpeed(ShipConfig.CARGO_SHIP_INIT_SPEED);
        super.setManeuverability(ShipConfig.CARGO_SHIP_INIT_MANEUVERABILITY);
        super.setShipType(ShipConfig.SHIP_TYPE_CARGOSHIP);
        super.setMaxCargoCapacity(ShipConfig.CARGO_SHIP_CARGO_CAPACITY);
        super.setCarriage(carriage);
    }

    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if (hpToSet > ShipConfig.CARGO_SHIP_MAX_HP)
            hpToSet = ShipConfig.CARGO_SHIP_MAX_HP;
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.CARGO_SHIP_MAX_HP);
    }

    @Override
    public void resetManeuverability() {
        super.setManeuverability(ShipConfig.CARGO_SHIP_INIT_MANEUVERABILITY);
    }

    @Override
    public void resetSpeed() {
        super.setSpeed(ShipConfig.CARGO_SHIP_INIT_SPEED);
        super.setMaxSpeed(ShipConfig.CARGO_SHIP_INIT_SPEED);
    }

    @Override
    public void initWeapons() {
    	super.initWeapons();
    	super.addWeapon(WeaponFactory.createWeapon(ShipConfig.CARGO_SHIP_INIT_WEAPON));
        super.selectWeapon(0);
    }
}
