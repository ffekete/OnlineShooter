package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.SpawnableItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class DoubleGatlingGun extends WeaponParent {

    public DoubleGatlingGun() {
        Spawner.spawn(this);
        super.setName(SpawnableItemType.DOUBLE_GATLING_GUN.getVisibleName());
		super.setType(SpawnableItemType.DOUBLE_GATLING_GUN);
		super.setAmmoType(AmmoType.BULLET);
		super.setShotCount(WeaponConfig.DOUBLE_GATLING_GUN_INIT_SHOT_COUNT);
		super.setShotAngle(WeaponConfig.DOUBLE_GATLING_GUN_INIT_SHOT_ANGLE);
		super.setRateOfFire(WeaponConfig.DOUBLE_GATLING_GUN_INIT_RATE_OF_FIRE);
		super.setDamage(AmmoType.BULLET.getDamage(0));
    }
    
    @Override
    public void increaseRateOfFire(long bonus) {
        this.setRateOfFire(WeaponConfig.DOUBLE_GATLING_GUN_INIT_RATE_OF_FIRE + WeaponConfig.DOUBLE_GATLING_GUN_RATE_OF_FIRE_BONUS * bonus);
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
