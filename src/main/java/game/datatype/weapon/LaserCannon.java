package game.datatype.weapon;

import java.util.ArrayList;
import java.util.List;

import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.datatype.PlayerData;
import game.datatype.bullet.LaserBeam;
import game.interfaces.Bullet;
import game.service.Spawner;

public class LaserCannon extends WeaponParent {

    public List<Bullet> createBullet(PlayerData player) {
        ArrayList<Bullet> bulletsToCreate = new ArrayList<>();

        bulletsToCreate.add(new LaserBeam(player.getCoordinate(), player.getShipAngle(), player.getId(),
                player.getWeapon().getDamage()));
        return bulletsToCreate;
    }

    public LaserCannon() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.LASER_CANNON_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.LASER_CANNON_INIT_AMMO);
        super.setName(ItemType.LASER_CANNON.getVisibleName());
        super.setRateOfFire(WeaponConfig.LASER_CANNON_INIT_RATE_OF_FIRE);
        super.setType(ItemType.LASER_CANNON);
    }
}
