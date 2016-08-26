package factory;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ItemType;
import game.datatype.weapon.DoubleGatlingGun;
import game.datatype.weapon.GatlingGun;
import game.datatype.weapon.LaserCannon;
import game.datatype.weapon.Shotgun;
import game.interfaces.Weapon;

public class WeaponFactoryTest {
    @Test
    public void testShouldCreateGatlingGun() {
        Weapon weapon = WeaponFactory.createWeapon(ItemType.GATLING_GUN);
        Assert.assertTrue(weapon instanceof GatlingGun);
    }

    @Test
    public void testShouldCreateDoubleGatlingGun() {
        Weapon weapon = WeaponFactory.createWeapon(ItemType.DOUBLE_GATLING_GUN);
        Assert.assertTrue(weapon instanceof DoubleGatlingGun);
    }

    @Test
    public void testShouldCreateLasetCannon() {
        Weapon weapon = WeaponFactory.createWeapon(ItemType.LASER_CANNON);
        Assert.assertTrue(weapon instanceof LaserCannon);
    }

    @Test
    public void testShouldCreateShotgun() {
        Weapon weapon = WeaponFactory.createWeapon(ItemType.SHOTGUN);
        Assert.assertTrue(weapon instanceof Shotgun);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShouldThrowRuntimeException() {
        WeaponFactory.createWeapon(ItemType.ATOM_SHIELD);
    }
}
