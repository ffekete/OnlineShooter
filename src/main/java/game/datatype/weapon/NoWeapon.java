package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class NoWeapon extends WeaponParent {
	
    public NoWeapon() {
	    Spawner.spawn(this);
	    super.setName(ItemType.NO_WEAPON.getVisibleName());
	    super.setType(ItemType.NO_WEAPON);
		super.setAmmoType(AmmoType.NO_AMMO);
		super.setShotCount(WeaponConfig.NO_WEAPON_INIT_SHOT_COUNT);
		super.setShotAngle(WeaponConfig.NO_WEAPON_INIT_SHOT_ANGLE);
		super.setRateOfFire(WeaponConfig.NO_WEAPON_INIT_RATE_OF_FIRE);
		super.setDamage(AmmoType.NO_AMMO.getDamage(0));
	}

@Override
public void increaseRateOfFire(long bonus) {
    return;
}

@Override
public void increaseDamage(long bonus) {
    return;
}

@Override
public long getInitAmmoCount() {
	return WeaponConfig.NO_WEAPON_INIT_AMMO_COUNT;
}
}
