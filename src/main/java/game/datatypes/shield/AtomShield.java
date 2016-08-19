package game.datatypes.shield;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.config.constants.ShieldConfig;

@Component
public class AtomShield extends ShieldParent {

    @Autowired
    private MessageSource messages;

    public AtomShield() {
        setName(messages.getMessage("shield.atom", null, Locale.US));
        setMaxProtectionValue(ShieldConfig.ATOM_SHIELD_PROTECTION);
        setProtection(getMaxProtectionValue());
    }
}
