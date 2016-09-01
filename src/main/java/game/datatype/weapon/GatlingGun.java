package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class GatlingGun extends WeaponParent {

	public GatlingGun() {
		Spawner.spawn(this);
        super.setName(ItemType.GATLING_GUN.getVisibleName());
        super.setType(ItemType.GATLING_GUN);
		super.setAmmoType(AmmoType.BULLET);
		super.setShotCount(WeaponConfig.GATLING_GUN.getInitShotCount());
		super.setShotAngle(WeaponConfig.GATLING_GUN.getInitShotAngle());
		super.setRateOfFire(WeaponConfig.GATLING_GUN.getInitRateOfFire());
		super.setDamage(AmmoType.BULLET.getDamage(0));
	}
	
	@Override
    public void increaseRateOfFire(long bonus) {
        this.setRateOfFire(WeaponConfig.GATLING_GUN.getInitRateOfFire() + WeaponConfig.GATLING_GUN.getRateOfFireBonus() * bonus);
    }
    
    @Override
    public void increaseDamage(long bonus) {
        super.setDamage(AmmoType.BULLET.getDamage(bonus));
    }
    
    @Override
	public long getInitAmmoCount() {
		return WeaponConfig.GATLING_GUN.getInitAmmoCount();
	}
    
    @Override
    public void startCooldownEffect() {
        this.setCooldown(WeaponConfig.GATLING_GUN.getRateOfFireTimeesCooldown() / this.getRateOfFire());
    }
}
