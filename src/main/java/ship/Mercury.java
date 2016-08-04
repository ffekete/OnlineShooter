package ship;

import config.ShipConfig;
import model.Coordinate;

public class Mercury extends ShipParent{

	public Mercury(){
		super.setCoordinates(new Coordinate());
		this.setHp(ShipConfig.MERCURY_INIT_HP);
		super.setSpeed(ShipConfig.MERCURY_INIT_SPEED);
		super.setMaxSpeed(ShipConfig.MERCURY_INIT_SPEED);
		super.setManeuverability(ShipConfig.MERCURY_INIT_MANEUVERABILITY);
		super.setShipType("Mercury");
	}
	
	@Override
	public void setHp(long hp) {
		long hpToSet = super.getHp() + hp;
		if(hpToSet > ShipConfig.MERCURY_INIT_HP)
			hpToSet = ShipConfig.MERCURY_INIT_HP;
		super.setHp(hp);
	}
	
	@Override
	public void resetHp() {
		this.setHp(ShipConfig.MERCURY_INIT_HP);	
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
	
}
