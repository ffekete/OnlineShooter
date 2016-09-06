package factory;

import game.config.constant.ItemType;
import game.datatype.shield.AtomShield;
import game.datatype.shield.NoShield;
import game.datatype.shield.NormalShield;
import game.datatype.shield.PlasmaShield;
import game.interfaces.Shield;

public abstract class ShieldFactory {

    public static Shield createShield(ItemType shieldId) {
        switch (shieldId) {
        case NORMAL_SHIELD:
            return new NormalShield();
        case PLASMA_SHIELD:
            return new PlasmaShield();
        case ATOM_SHIELD:
            return new AtomShield();
        case NO_SHIELD:
            return new NoShield();
        default:
            throw new RuntimeException("Unknownw shield type!");
        }
    }
}