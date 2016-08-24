package game.datatype.shield;

import game.config.constant.ShieldConfig;
import game.service.Spawner;

public class NormalShield extends ShieldParent {
    public NormalShield() {
        Spawner.spawn(this);
        setName("Normal shield");
        setMaxProtectionValue(ShieldConfig.NORMAL_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
