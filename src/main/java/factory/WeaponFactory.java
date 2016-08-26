package factory;

import game.config.constant.ItemType;
import game.datatype.weapon.DoubleGatlingGun;
import game.datatype.weapon.GatlingGun;
import game.datatype.weapon.LaserCannon;
import game.datatype.weapon.NoWeapon;
import game.datatype.weapon.Shotgun;
import game.interfaces.Weapon;

public class WeaponFactory {

    public static Weapon createWeapon(ItemType weaponId) {
        switch (weaponId) {
        case GATLING_GUN:
            return new GatlingGun();
        case LASER_CANNON:
            return new LaserCannon();
        case DOUBLE_GATLING_GUN:
            return new DoubleGatlingGun();
        case SHOTGUN:
            return new Shotgun();
        case NO_WEAPON:
            return new NoWeapon();
        default:
            throw new RuntimeException("Unknownw weapon type!");
        }
    }
}
