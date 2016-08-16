package factory;

import game.config.WeaponId;
import game.datatypes.weapons.DoubleGatlingGun;
import game.datatypes.weapons.GatlingGun;
import game.datatypes.weapons.LaserCannon;
import game.datatypes.weapons.Machinegun;
import game.datatypes.weapons.Shotgun;
import game.interfaces.Weapon;

public class WeaponFactory {

    public static Weapon createWeapon(WeaponId weaponId) {
        switch (weaponId) {
        case MACHINEGUN:
            return new Machinegun();
        case GATLING_GUN:
            return new GatlingGun();
        case LASER_CANNON:
            return new LaserCannon();
        case DOUBLE_GATLING:
            return new DoubleGatlingGun();
        case SHOTGUN:
            return new Shotgun();
        default:
            throw new RuntimeException("Unknownw weapon type!");
        }
    }
}
