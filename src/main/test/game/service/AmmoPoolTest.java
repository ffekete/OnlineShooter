package game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;
import game.datatype.weapon.GatlingGun;
import game.entrypoint.Application;
import game.interfaces.Ammo;
import game.interfaces.AmmoPoolList;
import game.interfaces.PlayerPoolMap;

@ContextConfiguration(classes = { Application.class })
public class AmmoPoolTest extends AbstractTestNGSpringContextTests {

    @Autowired
    PlayerPoolMap<Long, PlayerData> pp;

    @Autowired
    AmmoPoolList<Ammo> ap;

    PlayerData player;
    
    AIDao aiDao; 

    @BeforeMethod
    public void initBulletPool() {
        ap.clear();
        aiDao = new AIDao(false, false);
        player = new PlayerData(200L, "P01", ShipConfig.DELTAWING, aiDao);
    }

    @Test
    public void testShouldCreateTwoBulletsForTwoDifferentPlayers() {
        // given
        PlayerData player2 = new PlayerData(300L, "P02", ShipConfig.DELTAWING, aiDao);
        player2.setWeapon(new GatlingGun());
        player.setWeapon(new GatlingGun()); // setting this weapon for player
                                            // ship because it will create one
                                            // bullet
        pp.put(200L, player);
        pp.put(300L, player2);

        // when
        ap.addAmmo(player.getId());
        ap.addAmmo(player2.getId());

        // then
        Assert.assertEquals(ap.poolSize(), 2);
    }

    @Test
    public void testShouldCreateOneBullet_CooldownEffectActivated() {
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player
                                            // ship because it will create one
                                            // bullet
        pp.put(200L, player);

        // when
        ap.addAmmo(player.getId());
        ap.addAmmo(player.getId());

        // then
        Assert.assertEquals(ap.poolSize(), 1);
    }

    @Test
    public void testShouldCreateOneBullet() {
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player
                                            // ship because it will create one
                                            // bullet
        pp.put(200L, player);

        // when
        ap.addAmmo(player.getId());

        // then
        Assert.assertEquals(ap.poolSize(), 1);
    }

    @Test
    public void testShouldClearThePool() {
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player
                                            // ship because it will create one
                                            // bullet
        pp.put(10L, player);

        ap.addAmmo(10L);
        Assert.assertEquals(ap.poolSize(), 1);

        // get rid of waiting for player to be able to shoot again
        player.getWeapon().decreaseCooldownValue(player.getWeapon().getCooldown());

        ap.addAmmo(10L);
        Assert.assertEquals(ap.poolSize(), 2);

        ap.clear();
        Assert.assertEquals(ap.poolSize(), 0);
    }
}
