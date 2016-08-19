package game.datatypes.shield;

import game.config.constants.ShieldConfig;

public class PlasmaShield extends ShieldParent{
    public PlasmaShield(){
        setName("Plasma shield");
        setMaxProtectionValue(ShieldConfig.PLASMA_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
