package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class LaserCannon extends WeaponParent {

    public LaserCannon() {
        Spawner.spawn(this);
        super.setName(ItemType.LASER_CANNON.getVisibleName());
        super.setType(ItemType.LASER_CANNON);
		super.setAmmoType(AmmoType.LASER_BEAM);
		super.setShotCount(WeaponConfig.LASER_CANNON.getInitShotCount());
		super.setShotAngle(WeaponConfig.LASER_CANNON.getInitShotAngle());
		super.setRateOfFire(WeaponConfig.LASER_CANNON.getInitRateOfFire());
		super.setDamage(AmmoType.LASER_BEAM.getDamage(0));
    }
    
    @Override
    public void increaseRateOfFire(long bonus) {
        this.setRateOfFire(WeaponConfig.LASER_CANNON.getInitRateOfFire() + WeaponConfig.LASER_CANNON.getRateOfFireBonus() * bonus);
    }
    
    @Override
    public void increaseDamage(long bonus) {
        super.setDamage(AmmoType.LASER_BEAM.getDamage(bonus));
    }
    
    @Override
	public long getInitAmmoCount() {
		return WeaponConfig.LASER_CANNON.getInitAmmoCount();
	}
    
    @Override
    public void startCooldownEffect() {
        this.setCooldown(WeaponConfig.LASER_CANNON.getRateOfFireTimeesCooldown() / this.getRateOfFire());
    }
}
