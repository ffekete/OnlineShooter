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
        Weapon weapon = null;
        switch (weaponId) {
        case MACHINEGUN:
            weapon = new Machinegun();
            break;

        case GATLING_GUN:
            weapon = new GatlingGun();
            break;

        case LASER_CANNON:
            weapon = new LaserCannon();
            break;
        case DOUBLE_GATLING:
            weapon = new DoubleGatlingGun();
            break;
        case SHOTGUN:
            weapon = new Shotgun();
            break;
        default:
            break;
        }

        return weapon;
    }
}
