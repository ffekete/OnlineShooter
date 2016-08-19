package game.datatypes.weapons;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import factory.BulletBuilder;
import game.config.constants.WeaponConfig;
import game.datatypes.PlayerData;
import game.interfaces.Bullet;
import game.service.Spawner;

@Component
public class DoubleGatlingGun extends WeaponParent {

    @Autowired
    private MessageSource messages;

    public List<Bullet> createBullet(PlayerData player) {
        ArrayList<Bullet> bulletsToCreate = new ArrayList<>();

        bulletsToCreate.add(new BulletBuilder().setCoordinate(new Point2D.Double(player.getX(), player.getY()))
                .setAngle(player.getShipAngle() + 0.5d).setPlayerId(player.getId())
                .setDamage(player.getWeapon().getDamage()).build());

        bulletsToCreate.add(new BulletBuilder().setCoordinate(new Point2D.Double(player.getX(), player.getY()))
                .setAngle(player.getShipAngle() - 0.5d).setPlayerId(player.getId())
                .setDamage(player.getWeapon().getDamage()).build());

        return bulletsToCreate;
    }

    public DoubleGatlingGun() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.GATLING_GUN_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.GATLING_GUN_INIT_AMMO);
        super.setName(messages.getMessage("weapon.doublegatlinggun", null, Locale.US));
        super.setRateOfFire(WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE);
    }
}
