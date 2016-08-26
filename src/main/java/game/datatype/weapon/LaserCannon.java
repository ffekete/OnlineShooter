package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.SpawnableItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class LaserCannon extends WeaponParent {

    public LaserCannon() {
        Spawner.spawn(this);
        super.setName(SpawnableItemType.LASER_CANNON.getVisibleName());
		super.setType(SpawnableItemType.LASER_CANNON);
		super.setAmmoType(AmmoType.LASER_BEAM);
		super.setShotCount(WeaponConfig.LASER_CANNON_INIT_SHOT_COUNT);
		super.setShotAngle(WeaponConfig.LASER_CANNON_INIT_SHOT_ANGLE);
		super.setRateOfFire(WeaponConfig.LASER_CANNON_INIT_RATE_OF_FIRE);
		super.setDamage(AmmoType.LASER_BEAM.getDamage(0));
    }
    
    @Override
    public void increaseRateOfFire(long bonus) {
        this.setRateOfFire(WeaponConfig.LASER_CANNON_INIT_RATE_OF_FIRE + WeaponConfig.LASER_CANNON_RATE_OF_FIRE_BONUS * bonus);
    }
    
    @Override
    public void increaseDamage(long bonus) {
        super.setDamage(AmmoType.LASER_BEAM.getDamage(bonus));
    }
    
    @Override
	public long getInitAmmoCount() {
		return WeaponConfig.LASER_CANNON_INIT_AMMO_COUNT;
	}
}
