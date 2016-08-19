package game.datatype.shield;

import game.config.constant.ShieldConfig;

public class AtomShield extends ShieldParent{
    public AtomShield(){
        setName("Atom shield");
        setMaxProtectionValue(ShieldConfig.ATOM_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
