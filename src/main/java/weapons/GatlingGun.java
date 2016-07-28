package weapons;

import config.WeaponConfig;
import model.PlayerData;
import service.Spawner;

public class GatlingGun extends WeaponParent{

	public GatlingGun(){
		Spawner.spawn(this);
		this.setDamage(WeaponConfig.GATLING_GUN_INIT_DAMAGE);
		this.setAmmo(WeaponConfig.GATLING_GUN_INIT_AMMO);
		this.setName("Gatling gun");
		this.setRateOfFire(WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE);
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.setWeapon(this);		
	}
} 
