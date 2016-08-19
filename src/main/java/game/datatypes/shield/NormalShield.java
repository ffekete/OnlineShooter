package game.datatypes.shield;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.config.constants.ShieldConfig;

@Component
public class NormalShield extends ShieldParent {

    @Autowired
    private MessageSource messages;

    public NormalShield() {
        setName(messages.getMessage("shield.normal", null, Locale.US));
        setMaxProtectionValue(ShieldConfig.NORMAL_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
