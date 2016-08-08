package factory;

import org.springframework.stereotype.Component;

import game.config.ShieldId;
import game.datatypes.shield.AtomShield;
import game.datatypes.shield.NormalShield;
import game.datatypes.shield.PlasmaShield;
import game.interfaces.Shield;

@Component
public class ShieldFactory {

	public static Shield createShield(ShieldId shieldId) {
		Shield shield = null;
		switch (shieldId) {
		case NORMAL_SHIELD:
			shield = new NormalShield();
			break;

		case PLASMA_SHIELD:
			shield = new PlasmaShield();
			break;

		case ATOM_SHIELD:
			shield = new AtomShield();
			break;

		default:
			break;
		}

		return shield;
	}
}
