package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.SpawnableItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class GatlingGun extends WeaponParent {

	public GatlingGun() {
		Spawner.spawn(this);
		super.setName(SpawnableItemType.GATLING_GUN.getVisibleName());
		super.setType(SpawnableItemType.GATLING_GUN);
		super.setAmmoType(AmmoType.BULLET);
		super.setShotCount(WeaponConfig.GATLING_GUN_INIT_SHOT_COUNT);
		super.setShotAngle(WeaponConfig.GATLING_GUN_INIT_SHOT_ANGLE);
		super.setRateOfFire(WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE);
		super.setDamage(AmmoType.BULLET.getDamage(0));
	}
	
	@Override
    public void increaseRateOfFire(long bonus) {
        this.setRateOfFire(WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE + WeaponConfig.GATLING_GUN_RATE_OF_FIRE_BONUS * bonus);
    }
    
    @Override
    public void increaseDamage(long bonus) {
        super.setDamage(AmmoType.BULLET.getDamage(bonus));
    }
    
    @Override
	public long getInitAmmoCount() {
		return WeaponConfig.GATLING_GUN_INIT_AMMO_COUNT;
	}
}
