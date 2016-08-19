package game.datatypes.shield;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.config.constants.ShieldConfig;

@Component
public class PlasmaShield extends ShieldParent {

    @Autowired
    private MessageSource messages;

    public PlasmaShield() {
        setName(messages.getMessage("shield.normal", null, Locale.US));
        setMaxProtectionValue(ShieldConfig.PLASMA_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
