package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class DoubleGatlingGun extends WeaponParent {

    public DoubleGatlingGun() {
        Spawner.spawn(this);
        super.setName(ItemType.DOUBLE_GATLING_GUN.getVisibleName());
        super.setType(ItemType.DOUBLE_GATLING_GUN);
		super.setAmmoType(AmmoType.BULLET);
		super.setShotCount(WeaponConfig.DOUBLE_GATLING_GUN.getInitShotCount());
		super.setShotAngle(WeaponConfig.DOUBLE_GATLING_GUN.getInitShotAngle());
		super.setRateOfFire(WeaponConfig.DOUBLE_GATLING_GUN.getInitRateOfFire());
		super.setDamage(AmmoType.BULLET.getDamage(0));
    }
    
    @Override
    public void increaseRateOfFire(long bonus) {
        this.setRateOfFire(WeaponConfig.DOUBLE_GATLING_GUN.getInitRateOfFire() + WeaponConfig.DOUBLE_GATLING_GUN.getRateOfFireBonus() * bonus);
    }
    
    @Override
    public void increaseDamage(long bonus) {
        super.setDamage(AmmoType.BULLET.getDamage(bonus));
    }
    
    @Override
	public long getInitAmmoCount() {
		return WeaponConfig.DOUBLE_GATLING_GUN.getInitAmmoCount();
	}
    
    @Override
    public void startCooldownEffect() {
        this.setCooldown(WeaponConfig.DOUBLE_GATLING_GUN.getRateOfFireTimeesCooldown() / this.getRateOfFire());
    }
}
