package ship;

import config.ShipConfig;

public class Interceptor extends ShipParent{

	public Interceptor(){
		super.setHp(ShipConfig.INTERCEPTOR_INIT_HP);
		super.setMaxSpeed(ShipConfig.INTERCEPTOR_INIT_SPEED);
		super.setManeuverability(ShipConfig.INTERCEPTOR_INIT_MANEUVERABILITY);
		super.setShipType("Interceptor");
	}
	
	@Override
	public void setHp(long hp) {
		long hpToSet = super.getHp() + hp;
		if(hpToSet > ShipConfig.INTERCEPTOR_INIT_HP)
			hpToSet = ShipConfig.INTERCEPTOR_INIT_HP;
		super.setHp(hp);
	}
	
}
