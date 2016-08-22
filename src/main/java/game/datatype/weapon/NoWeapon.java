package game.datatype.weapon;

import java.util.ArrayList;
import java.util.List;

import game.config.constant.WeaponConfig;
import game.datatype.PlayerData;
import game.interfaces.Bullet;
import game.service.Spawner;

public class NoWeapon extends WeaponParent {
    public NoWeapon() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.NO_WEAPON_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.NO_WEAPON_INIT_AMMO);
        super.setName("No_weapon");
        super.setRateOfFire(WeaponConfig.NO_WEAPON_INIT_RATE_OF_FIRE);
    }

    public List<Bullet> createBullet(PlayerData player) {
        ArrayList<Bullet> bulletsToCreate = new ArrayList<>();

        return bulletsToCreate;
    }
}
