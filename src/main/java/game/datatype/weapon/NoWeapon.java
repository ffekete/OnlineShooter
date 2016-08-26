package game.datatype.weapon;

import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class NoWeapon extends WeaponParent {
    public NoWeapon() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.NO_WEAPON_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.NO_WEAPON_INIT_AMMO);
        super.setName(ItemType.NO_WEAPON.getVisibleName());
        super.setRateOfFire(WeaponConfig.NO_WEAPON_INIT_RATE_OF_FIRE);
        super.setType(ItemType.NO_WEAPON);
    }
}
