package game.datatype;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import factory.WeaponFactory;
import game.config.constant.ItemType;
import game.config.constant.ShipConfig;
import game.config.constant.WeaponConfig;
import game.interfaces.Ammo;

public class PlayerDataTest {

    AIDao aiDao;
    PlayerData pD;

    @BeforeMethod
    public void init() {
        aiDao = new AIDao(false, false);
        pD = new PlayerData(1L, "Test", ShipConfig.getSpecificConfig(ShipConfig.QUICKSILVER.getType()), aiDao);
    }

    @Test
    public void testShouldCreateSpecificPlayer() {
        Assert.assertTrue(pD instanceof PlayerData);
        Assert.assertEquals(pD.getId(), Long.valueOf(1L));
        Assert.assertEquals(pD.getName(), "Test");
        Assert.assertEquals(pD.getShipType(), ShipConfig.QUICKSILVER.getType());
        Assert.assertEquals(pD.getIsAI(), false);
        Assert.assertEquals(pD.getIsAsteroid(), false);
    }

    @Test
    public void testShouldUpdateCanvasPropertiesPerfectly() {
        pD.updateCanvasProperties(1, 1, 10, 10);

        Assert.assertEquals(pD.getCanvas().getX(), 1.0);
        Assert.assertEquals(pD.getCanvas().getY(), 1.0);
        Assert.assertEquals(pD.getCanvas().getWidth(), 10.0);
        Assert.assertEquals(pD.getCanvas().getHeight(), 10.0);
    }

    @Test
    public void testShouldChangeWeaponToLaserCannon() {
        pD.addWeapon(WeaponFactory.createWeapon(ItemType.LASER_CANNON));
        pD.selectWeapon(1);
        Assert.assertEquals(pD.getWeapon().getType(), ItemType.LASER_CANNON);
    }

    @Test
    public void testShouldGiveBackProperActualWeaponAmmoCount() {
        pD.addWeapon(WeaponFactory.createWeapon(ItemType.SHOTGUN));
        Assert.assertEquals(pD.getActualWeaponAmmoCount(), 800L);
    }

    @Test
    public void testShouldDecreaseActualWeanpoAmmoCount() {
        Long actualAmmoCount = pD.getActualWeaponAmmoCount();
        pD.decreasAmmoForPlayerWeapon();
        Assert.assertTrue(pD.getActualWeaponAmmoCount() < actualAmmoCount);
    }

    @Test
    public void testShouldCreateAmmoForActualWeapon() {
        List<Ammo> ammoList = pD.createAmmoWithPlayerWeapon();
        Assert.assertTrue(ammoList.size() > 0);
        Assert.assertTrue(ammoList.get(0) instanceof Ammo);
    }

    @Test
    public void testShouldGiveBackFalseForCanShootWeapon() {
        while (pD.getSpaceShip().getAmmoCount().size() > 0) {
            pD.decreasAmmoForPlayerWeapon();
        }
        Assert.assertFalse(pD.canShootWeapon());
    }

    @Test
    public void testShouldGivebackProperCooldown() {
        pD.startShootingRateCooldownEffect();
        Assert.assertTrue(pD.getWeapon().getCooldown() == WeaponConfig.RATE_OF_FIRE_TIMES_COOLDOWN
                / pD.getWeapon().getRateOfFire());
    }
}
