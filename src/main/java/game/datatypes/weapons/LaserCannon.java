package game.datatypes.weapons;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.config.constants.WeaponConfig;
import game.datatypes.PlayerData;
import game.datatypes.bullet.LaserBeam;
import game.interfaces.Bullet;
import game.service.Spawner;

@Component
public class LaserCannon extends WeaponParent {

    @Autowired
    private MessageSource messages;

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
        super.setName(messages.getMessage("weapon.lasercannon", null, Locale.US));
        super.setRateOfFire(WeaponConfig.LASER_CANNON_INIT_RATE_OF_FIRE);
    }
}
