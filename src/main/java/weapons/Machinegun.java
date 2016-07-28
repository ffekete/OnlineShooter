package weapons;

import config.WeaponConfig;
import model.PlayerData;
import service.Spawner;

public class Machinegun extends WeaponParent{

	public Machinegun(){
		Spawner.spawn(this);
		super.setDamage(WeaponConfig.MACHINEGUN_INIT_DAMAGE);
		this.setAmmo(WeaponConfig.MACHINEGUN_INIT_AMMO);
		this.setName("Machinegun");
		super.setRateOfFire(WeaponConfig.MACHINEGUN_INIT_RATE_OF_FIRE);
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.setWeapon(this);		
	}
} 
