package game.datatypes.weapons;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.config.constants.WeaponConfig;
import game.service.Spawner;

@Component
public class Machinegun extends WeaponParent {

    @Autowired
    private MessageSource messages;

    public Machinegun() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.MACHINEGUN_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.MACHINEGUN_INIT_AMMO);
        super.setName(messages.getMessage("weapon.machinegun", null, Locale.US));
        super.setRateOfFire(WeaponConfig.MACHINEGUN_INIT_RATE_OF_FIRE);
    }
}
