package weapons;

import config.WeaponConfig;
import model.PlayerData;
import service.Spawner;

public class LaserCannon extends WeaponParent {

	public LaserCannon() {
		Spawner.spawn(this);
		super.setDamage(WeaponConfig.LASER_CANNON_INIT_DAMAGE);
		super.setAmmo(WeaponConfig.LASER_CANNON_INIT_AMMO);
		super.setName("Laser cannon");
		super.setRateOfFire(WeaponConfig.LASER_CANNON_INIT_RATE_OF_FIRE);
	}
}
