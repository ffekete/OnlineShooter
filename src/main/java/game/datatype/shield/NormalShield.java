package game.datatype.shield;

import game.config.constant.ShieldConfig;

public class NormalShield extends ShieldParent{
    public NormalShield(){
        setName("Normal shield");
        setMaxProtectionValue(ShieldConfig.NORMAL_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
