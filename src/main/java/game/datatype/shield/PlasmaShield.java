package game.datatype.shield;

import game.config.constant.ShieldConfig;
import game.service.Spawner;

public class PlasmaShield extends ShieldParent {
    public PlasmaShield() {
        Spawner.spawn(this);
        setName("Plasma shield");
        setMaxProtectionValue(ShieldConfig.PLASMA_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
