package game.datatype.weapon;

import game.config.WeaponId;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class GatlingGun extends WeaponParent {

    public GatlingGun() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.GATLING_GUN_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.GATLING_GUN_INIT_AMMO);
        super.setName("Gatling gun");
        super.setRateOfFire(WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE);
        super.setType(WeaponId.GATLING_GUN);
    }
}
