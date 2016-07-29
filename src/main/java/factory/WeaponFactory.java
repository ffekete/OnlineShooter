package factory;

import config.WeaponId;
import interfaces.Weapon;
import weapons.GatlingGun;
import weapons.LaserCannon;
import weapons.Machinegun;

public class WeaponFactory {

	public Weapon createWeapon(WeaponId weaponId) {
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

		default:
			break;
		}

		return weapon;
	}
}
