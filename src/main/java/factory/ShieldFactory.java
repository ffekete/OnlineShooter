package factory;

import org.springframework.stereotype.Component;

import game.config.ShieldId;
import game.datatypes.shield.AtomShield;
import game.datatypes.shield.NormalShield;
import game.datatypes.shield.PlasmaShield;
import game.interfaces.Shield;

@Component
public abstract class ShieldFactory {

    public static Shield createShield(ShieldId shieldId) {
        switch (shieldId) {
        case NORMAL_SHIELD:
            return new NormalShield();
        case PLASMA_SHIELD:
            return new PlasmaShield();
        case ATOM_SHIELD:
            return new AtomShield();
        default:
            throw new RuntimeException("Unknownw shield type!");
        }
    }
}