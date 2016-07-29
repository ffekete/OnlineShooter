package weapons;

import config.WeaponConfig;
import service.Spawner;

public class GatlingGun extends WeaponParent{

	public GatlingGun(){
		Spawner.spawn(this);
		super.setDamage(WeaponConfig.GATLING_GUN_INIT_DAMAGE);
		super.setAmmo(WeaponConfig.GATLING_GUN_INIT_AMMO);
		super.setName("Gatling gun");
		super.setRateOfFire(WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE);
	}
} 
