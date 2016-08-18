package game.datatypes.shield;

import game.config.constants.ShieldConfig;

public class AtomShield extends ShieldParent{
    public AtomShield(){
        setName("Atom shield");
        setMaxProtectionValue(ShieldConfig.ATOM_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
