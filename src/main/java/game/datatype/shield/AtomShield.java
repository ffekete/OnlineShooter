package game.datatype.shield;

import game.config.constant.ShieldConfig;
import game.service.Spawner;

public class AtomShield extends ShieldParent {
    public AtomShield() {
        Spawner.spawn(this);
        setName("Atom shield");
        setMaxProtectionValue(ShieldConfig.ATOM_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
