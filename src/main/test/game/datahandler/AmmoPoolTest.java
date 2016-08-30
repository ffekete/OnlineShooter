package game.datahandler;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;
import game.datatype.RegistrationData;
import game.entrypoint.Application;
import game.interfaces.Ammo;

@ContextConfiguration(classes = { Application.class })
public class AmmoPoolTest extends AbstractTestNGSpringContextTests {

    @Autowired
    PlayerPool playerPool;

    @Autowired
    AmmoPool ammoPool;

    public AIDao aiDao;

    @BeforeMethod
    public void init() {
        RegistrationData data = new RegistrationData();
        data.setName("Test");
        data.setShipType(ShipConfig.INTERCEPTOR.getType());
        data.setColor("#0000FF");
        data.setIsAI(false);
        playerPool.registerPlayer(1L, data);

        ammoPool.clear();

        aiDao = new AIDao(false, false);
    }

    // TODO: rendberakni a tesztet
    @Test(enabled = false)
    public void testShouldAddOneAmmo() {
        ammoPool.addAmmo(1L);
        Assert.assertEquals(ammoPool.poolSize(), 1);
    }

    @Test
    public void testShouldGiveBackAmmoIterator() {
        Assert.assertTrue(ammoPool.getIterator() instanceof Iterator);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testShouldThrowIllegalArgumentExceptionForBadIndex() {
        ammoPool.getNthAmmo(999);
    }

    @Test
    public void testShouldAddSpecificAmmoToPool() {
        PlayerData player = new PlayerData(999L, "Test", ShipConfig.QUICKSILVER, aiDao);
        List<Ammo> list = player.createAmmoWithPlayerWeapon();
        Assert.assertEquals(list.get(0).getPlayerId(), 999L);
    }

    @Test
    public void testShouldAddOneSpecificAmmo() {
        PlayerData player = new PlayerData(987L, "Test", ShipConfig.QUICKSILVER, aiDao);
        List<Ammo> list = player.createAmmoWithPlayerWeapon();
        ammoPool.add(list.get(0));
        Assert.assertEquals(ammoPool.poolSize(), 1);
    }

    @Test
    public void testShouldRemoveOneAmmo() {
        PlayerData player = new PlayerData(987L, "Test", ShipConfig.QUICKSILVER, aiDao);
        List<Ammo> list1 = player.createAmmoWithPlayerWeapon();
        List<Ammo> list2 = player.createAmmoWithPlayerWeapon();
        ammoPool.add(list1.get(0));
        ammoPool.add(list2.get(0));
        ammoPool.remove(list1.get(0));
        Assert.assertEquals(ammoPool.poolSize(), 1);
    }
}
