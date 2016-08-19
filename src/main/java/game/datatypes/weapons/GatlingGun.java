package game.datatypes.weapons;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.config.constants.WeaponConfig;
import game.service.Spawner;

@Component
public class GatlingGun extends WeaponParent {

    @Autowired
    private MessageSource messages;

    public GatlingGun() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.GATLING_GUN_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.GATLING_GUN_INIT_AMMO);
        super.setName(messages.getMessage("weapon.gatlinggun", null, Locale.US));
        super.setRateOfFire(WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE);
    }
}
