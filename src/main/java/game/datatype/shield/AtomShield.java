package game.datatype.shield;

import game.config.constant.ShieldConfig;
import game.config.constant.SpawnableItemType;
import game.service.Spawner;

public class AtomShield extends ShieldParent {
    public AtomShield() {
        Spawner.spawn(this);
        setName(SpawnableItemType.ATOM_SHIELD.getVisibleName());
        setMaxProtectionValue(ShieldConfig.ATOM_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
