package game.datatype.weapon;

import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class Machinegun extends WeaponParent {

    public Machinegun() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.MACHINEGUN_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.MACHINEGUN_INIT_AMMO);
        super.setName("Machinegun");
        super.setRateOfFire(WeaponConfig.MACHINEGUN_INIT_RATE_OF_FIRE);
    }
}
