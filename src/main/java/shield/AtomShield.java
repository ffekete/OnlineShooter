package shield;

import config.ShieldConfig;

public class AtomShield extends ShieldParent{
	public AtomShield(){
		setName("Atom shield");
		setMaxProtectionValue(ShieldConfig.ATOM_SHIELD_PROTECTION);
		setProtection(getMaxProtectionValue());
	}
}
