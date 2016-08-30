package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class Shotgun extends WeaponParent {

    public Shotgun() {
        Spawner.spawn(this);
        super.setName(ItemType.SHOTGUN.getVisibleName());
		super.setType(ItemType.SHOTGUN);
		super.setAmmoType(AmmoType.CANISTER);
		super.setShotCount(WeaponConfig.SHOTGUN_INIT_SHOT_COUNT);
		super.setShotAngle(WeaponConfig.SHOTGUN_INIT_SHOT_ANGLE);
		super.setRateOfFire(WeaponConfig.SHOTGUN_INIT_RATE_OF_FIRE);
		super.setDamage(AmmoType.CANISTER.getDamage(0));
    }
    
    @Override
    public void increaseRateOfFire(long bonus) {
        this.setRateOfFire(WeaponConfig.SHOTGUN_INIT_RATE_OF_FIRE + WeaponConfig.SHOTGUN_RATE_OF_FIRE_BONUS * bonus);
    }
    
    @Override
    public void increaseDamage(long bonus) {
        super.setDamage(AmmoType.CANISTER.getDamage(bonus));
    }

	@Override
	public long getInitAmmoCount() {
		return WeaponConfig.SHOTGUN_INIT_AMMO_COUNT;
	}
}
