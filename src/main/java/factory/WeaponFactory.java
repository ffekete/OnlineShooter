package factory;

import config.WeaponId;
import interfaces.Weapon;
import weapons.DoubleGatlingGun;
import weapons.GatlingGun;
import weapons.LaserCannon;
import weapons.Machinegun;
import weapons.Shotgun;

public class WeaponFactory {

	public static Weapon createWeapon(WeaponId weaponId) {
		Weapon weapon = null;
		switch (weaponId) {
		case MACHINEGUN:
			weapon = new Machinegun();
			break;

		case GATLING_GUN:
			weapon = new GatlingGun();
			break;

		case LASER_CANNON:
			weapon = new LaserCannon();
			break;
		case DOUBLE_GATLING:
			weapon = new DoubleGatlingGun();
			break;
		case SHOTGUN:
			weapon = new Shotgun();
			break;
		default:
			break;
		}

		return weapon;
	}
}
