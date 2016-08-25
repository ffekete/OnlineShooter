package game.datatype.shield;

import game.config.constant.ShieldConfig;
import game.config.constant.SpawnableItemType;
import game.service.Spawner;

public class NormalShield extends ShieldParent {
    public NormalShield() {
        Spawner.spawn(this);
        setName(SpawnableItemType.NORMAL_SHIELD.getVisibleName());
        setMaxProtectionValue(ShieldConfig.NORMAL_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
