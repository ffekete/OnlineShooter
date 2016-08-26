package game.datatype.shield;

import game.config.constant.ItemType;
import game.config.constant.ShieldConfig;
import game.service.Spawner;

public class NoShield extends ShieldParent {
    public NoShield() {
        Spawner.spawn(this);
        setName(ItemType.NO_SHIELD.getVisibleName());
        setMaxProtectionValue(ShieldConfig.NO_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
