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
public class Shotgun extends WeaponParent {

    @Autowired
    private MessageSource messages;

    public List<Bullet> createBullet(PlayerData player) {
        ArrayList<Bullet> bulletsToCreate = new ArrayList<>();

        bulletsToCreate.add(new BulletBuilder().setCoordinate(new Point2D.Double(player.getX(), player.getY()))
                .setAngle(player.getShipAngle() + 15.0d).setPlayerId(player.getId())
                .setDamage(player.getWeapon().getDamage()).build());

        bulletsToCreate.add(new BulletBuilder().setCoordinate(new Point2D.Double(player.getX(), player.getY()))
                .setAngle(player.getShipAngle() + 5.0d).setPlayerId(player.getId())
                .setDamage(player.getWeapon().getDamage()).build());

        bulletsToCreate.add(new BulletBuilder().setCoordinate(new Point2D.Double(player.getX(), player.getY()))
                .setAngle(player.getShipAngle()).setPlayerId(player.getId()).setDamage(player.getWeapon().getDamage())
                .build());

        bulletsToCreate.add(new BulletBuilder().setCoordinate(new Point2D.Double(player.getX(), player.getY()))
                .setAngle(player.getShipAngle() - 5.0d).setPlayerId(player.getId())
                .setDamage(player.getWeapon().getDamage()).build());

        bulletsToCreate.add(new BulletBuilder().setCoordinate(new Point2D.Double(player.getX(), player.getY()))
                .setAngle(player.getShipAngle() - 15.0d).setPlayerId(player.getId())
                .setDamage(player.getWeapon().getDamage()).build());

        return bulletsToCreate;
    }

    public Shotgun() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.SHOTGUN_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.SHOTGUN_INIT_AMMO);
        super.setName(messages.getMessage("weapon.shotgun", null, Locale.US));
        super.setRateOfFire(WeaponConfig.SHOTGUN_INIT_RATE_OF_FIRE);
    }
}
