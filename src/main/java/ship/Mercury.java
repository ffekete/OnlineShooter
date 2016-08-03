package ship;

import config.ShipConfig;

public class Mercury extends ShipParent{

	public Mercury(){
		super.setHp(ShipConfig.MERCURY_INIT_HP);
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
	
}
