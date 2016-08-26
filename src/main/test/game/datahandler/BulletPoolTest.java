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
import game.interfaces.Bullet;

@ContextConfiguration(classes = { Application.class })
public class BulletPoolTest extends AbstractTestNGSpringContextTests {

    @Autowired
    PlayerPool playerPool;

    @Autowired
    BulletPool bulletPool;

    public AIDao aiDao;

    @BeforeMethod
    public void init() {
        RegistrationData data = new RegistrationData();
        data.setName("Test");
        data.setShipType(ShipConfig.INTERCEPTOR.getType());
        data.setColor("#0000FF");
        data.setIsAI(false);
        playerPool.registerPlayer(1L, data);

        bulletPool.clear();

        aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
    }

    @Test
    public void testShouldAddOneBullet() {
        bulletPool.addBullet(563L);
        Assert.assertEquals(bulletPool.poolSize(), 1);
    }

    @Test
    public void testShouldGiveBackBulletIterator() {
        Assert.assertTrue(bulletPool.getIterator() instanceof Iterator);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testShouldThrowIllegalArgumentExceptionForBadIndex() {
        bulletPool.getNthBullet(999);
    }

    @Test
    public void testShouldAddSpecificBulletToPool() {
        PlayerData player = new PlayerData(999L, "Test", ShipConfig.QUICKSILVER, aiDao);
        List<Bullet> list = player.createBulletWithPlayerWeapon();
        Assert.assertEquals(list.get(0).getPlayerId(), 999L);
    }

    @Test
    public void testShouldAddOneSpecificBullet() {
        PlayerData player = new PlayerData(987L, "Test", ShipConfig.QUICKSILVER, aiDao);
        List<Bullet> list = player.createBulletWithPlayerWeapon();
        bulletPool.add(list.get(0));
        Assert.assertEquals(bulletPool.poolSize(), 1);
    }

    @Test
    public void testShouldRemoveOneBullet() {
        PlayerData player = new PlayerData(987L, "Test", ShipConfig.QUICKSILVER, aiDao);
        List<Bullet> list1 = player.createBulletWithPlayerWeapon();
        List<Bullet> list2 = player.createBulletWithPlayerWeapon();
        bulletPool.add(list1.get(0));
        bulletPool.add(list2.get(0));
        bulletPool.remove(list1.get(0));
        Assert.assertEquals(bulletPool.poolSize(), 1);
    }
}
