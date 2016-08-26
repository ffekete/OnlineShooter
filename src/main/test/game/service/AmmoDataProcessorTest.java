package game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import game.config.constant.AmmoConfig;
import game.config.constant.GameConfig;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;
import game.datatype.weapon.GatlingGun;
import game.entrypoint.Application;
import game.interfaces.Ammo;
import game.interfaces.AmmoDataProcessorInterface;
import game.interfaces.AmmoPoolList;
import game.interfaces.PlayerPoolMap;

@ContextConfiguration(classes = Application.class)
public class AmmoDataProcessorTest extends AbstractTestNGSpringContextTests {

    @Autowired
    PlayerPoolMap<Long, PlayerData> pp;

    @Autowired
    AmmoDataProcessorInterface adp;

    @Autowired
    AmmoPoolList<Ammo> ap;

    PlayerData player;

    @BeforeMethod
    public void initTests() {
        ap.clear();
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
        player = new PlayerData(200L, "P01", ShipConfig.DELTAWING, aiDao);
    }

    @DataProvider(name = "ammoDataInput")
    Object[][] ammoDataInput() {
        return new Object[][] {
                // x,y,angle,number of cycles to count, expectedX, expectedY
                { 0.0d, 0.0d, 0.0d, 1, AmmoConfig.BULLET_INIT_SPEED, 0.0D },
                { 0.0d, 0.0d, 0.0d, 3, 3 * AmmoConfig.BULLET_INIT_SPEED, 0.0D },
                { GameConfig.STAGE_POS_LIMIT_X, 0.0d, 0.0d, 1,
                        GameConfig.STAGE_NEG_LIMIT_X + AmmoConfig.BULLET_INIT_SPEED, 0.0D },
                { GameConfig.STAGE_NEG_LIMIT_X, 0.0d, 180.0d, 1,
                        GameConfig.STAGE_POS_LIMIT_X - AmmoConfig.BULLET_INIT_SPEED, 0.0D }, };
    }

    @Test(dataProvider = "ammoDataInput")
    public void testShouldUpdateAmmoCoordinates(double x, double y, double angle, int cycles, double expectedX,
            double expectedY) {
        // given
        // setting this weapon for player ship because it will create one ammo
        player.setWeapon(new GatlingGun());
        player.setCoordinate(x, y);
        player.setShipAngle(angle);
        pp.put(200L, player);
        ap.addAmmo(player.getId());

        // when
        for (int i = 1; i <= cycles; i++) {
            adp.updateAmmoData();
        }

        Ammo ammo = null;
        ammo = ap.getNthAmmo(0);

        double ammoX = ammo.getX();
        double ammoY = ammo.getY();

        // then

        Assert.assertNotNull(ammo);
        Assert.assertEquals(ammoX, expectedX, 0.0001,
                "Expected and calculated X coordinates does not match! (expected: " + expectedX + ", calculated: "
                        + ammoX + ")");
        Assert.assertEquals(ammoY, expectedY, 0.0001,
                "Expected and calculated Y coordinates does not match! (expected: " + expectedY + ", calculated: "
                        + ammoY + ")");
    }
}
