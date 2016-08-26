package game.datatype.weapon;

import java.util.ArrayList;
import java.util.List;

import factory.BulletBuilder;
import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.datatype.PlayerData;
import game.interfaces.Bullet;
import game.service.Spawner;

public class DoubleGatlingGun extends WeaponParent {

    public List<Bullet> createBullet(PlayerData player) {
        ArrayList<Bullet> bulletsToCreate = new ArrayList<>();
        
        bulletsToCreate.add(new BulletBuilder()
                            .setCoordinate(player.getCoordinate())
                            .setAngle(player.getShipAngle() + 0.5d).setPlayerId(player.getId())
                            .setDamage(player.getWeapon().getDamage())
                            .build());
        
        bulletsToCreate.add(new BulletBuilder()
                            .setCoordinate(player.getCoordinate())
                            .setAngle(player.getShipAngle() - 0.5d).setPlayerId(player.getId())
                            .setDamage(player.getWeapon().getDamage())
                            .build());

        return bulletsToCreate;
    }

    public DoubleGatlingGun() {
        Spawner.spawn(this);
        super.setDamage(WeaponConfig.GATLING_GUN_INIT_DAMAGE);
        super.setAmmo(WeaponConfig.GATLING_GUN_INIT_AMMO);
        super.setName(ItemType.DOUBLE_GATLING_GUN.getVisibleName());
        super.setRateOfFire(WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE);
        super.setType(ItemType.DOUBLE_GATLING_GUN);
    }
}
