package ship;

import config.ShipConfig;
import model.Coordinate;

public class Interceptor extends ShipParent{

	public Interceptor(){
		super.setCoordinates(new Coordinate());
		this.setHp(ShipConfig.INTERCEPTOR_INIT_HP);
		super.setSpeed(ShipConfig.INTERCEPTOR_INIT_SPEED);
		super.setMaxSpeed(ShipConfig.INTERCEPTOR_INIT_SPEED);
		super.setManeuverability(ShipConfig.INTERCEPTOR_INIT_MANEUVERABILITY);
		super.setShipType("Interceptor");
	}
	
	@Override
	public void setHp(long hp) {
		long hpToSet = hp;
		if(hpToSet > ShipConfig.INTERCEPTOR_INIT_HP)
			hpToSet = ShipConfig.INTERCEPTOR_INIT_HP;

		super.setHp(hpToSet);
	}

	@Override
	public void resetHp() {
		this.setHp(ShipConfig.INTERCEPTOR_INIT_HP);
		
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
}
