package game.datatype.shield;

import game.config.constant.ShieldConfig;
import game.config.constant.SpawnableItemType;
import game.service.Spawner;

public class PlasmaShield extends ShieldParent {
    public PlasmaShield() {
        Spawner.spawn(this);
        setName(SpawnableItemType.PLASMA_SHIELD.getVisibleName());
        setMaxProtectionValue(ShieldConfig.PLASMA_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
