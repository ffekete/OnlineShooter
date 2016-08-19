package game.datatype.weapon;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import game.config.constant.WeaponConfig;
import game.datatype.PlayerData;
import game.datatype.bullet.LaserBeam;
import game.interfaces.Bullet;
import game.service.Spawner;

public class LaserCannon extends WeaponParent {

    public List<Bullet> createBullet(PlayerData player) {
        ArrayList<Bullet> bulletsToCreate = new ArrayList<>();

        bulletsToCreate.add(new LaserBeam(new Point2D.Double(player.getX(), player.getY()), player.getShipAngle(),
                player.getId(), player.getWeapon().getDamage()));
        return bulletsToCreate;
    }

    public LaserCannon() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.LASER_CANNON_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.LASER_CANNON_INIT_AMMO);
        super.setName("Laser cannon");
        super.setRateOfFire(WeaponConfig.LASER_CANNON_INIT_RATE_OF_FIRE);
    }
}
