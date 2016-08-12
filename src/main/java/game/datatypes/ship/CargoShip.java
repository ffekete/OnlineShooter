package game.datatypes.ship;

import java.util.LinkedList;
import java.util.List;

import factory.WeaponFactory;
import game.config.ShipConfig;
import game.datatypes.Coordinate;
import game.interfaces.SpawnableItem;

public class CargoShip extends ShipParent{

	private List<SpawnableItem> carriage;
	
    public CargoShip(List<SpawnableItem> carriage){
        super.setCoordinates(new Coordinate());
        this.setHp(ShipConfig.CARGO_SHIP_INIT_HP);
        super.setSpeed(ShipConfig.CARGO_SHIP_INIT_SPEED);
        super.setMaxSpeed(ShipConfig.CARGO_SHIP_INIT_SPEED);
        super.setManeuverability(ShipConfig.CARGO_SHIP_INIT_MANEUVERABILITY);
        super.setShipType("Cargoship");
        this.carriage = carriage;
    }
    
    @Override
    public void setHp(long hp) {
        long hpToSet = hp;
        if(hpToSet > ShipConfig.CARGO_SHIP_INIT_HP)
            hpToSet = ShipConfig.CARGO_SHIP_INIT_HP;
        super.setHp(hpToSet);
    }

    @Override
    public void resetHp() {
        this.setHp(ShipConfig.CARGO_SHIP_INIT_HP);    
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
    public void initWeapon() {
        super.setWeapon(WeaponFactory.createWeapon(ShipConfig.CARGO_SHIP_INIT_WEAPON));
    }
    
    public List<SpawnableItem> dropItem(){
    	return this.carriage;
    }
}
