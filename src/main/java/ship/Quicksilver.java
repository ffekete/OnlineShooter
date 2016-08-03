package ship;

import config.ShipConfig;

public class Quicksilver extends ShipParent{

	public Quicksilver(){
		super.setHp(ShipConfig.QUICKSILVER_INIT_HP);
		super.setMaxSpeed(ShipConfig.QUICKSILVER_INIT_SPEED);
		super.setManeuverability(ShipConfig.QUICKSILVER_INIT_MANEUVERABILITY);
		super.setShipType("Quicksilver");
	}
	
	@Override
	public void setHp(long hp) {
		long hpToSet = super.getHp() + hp;
		if(hpToSet > ShipConfig.QUICKSILVER_INIT_HP)
			hpToSet = ShipConfig.QUICKSILVER_INIT_HP;
		super.setHp(hp);
	}
	
}
