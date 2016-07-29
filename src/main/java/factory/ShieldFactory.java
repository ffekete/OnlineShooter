package factory;

import org.springframework.stereotype.Component;

import config.ShieldId;
import interfaces.Shield;
import shield.AtomShield;
import shield.NormalShield;
import shield.PlasmaShield;

@Component
public class ShieldFactory {

	public Shield createShield(ShieldId shieldId) {
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
