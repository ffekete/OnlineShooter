package game.datatypes.shield;

import game.config.ShieldConfig;

public class NormalShield extends ShieldParent{
    public NormalShield(){
        setName("Normal shield");
        setMaxProtectionValue(ShieldConfig.NORMAL_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}