package game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.ShipConfig;
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
    AmmoPoolList<Ammo> bp;

    PlayerData player;

    @BeforeMethod
    public void initBulletPool() {
        bp.clear();
        player = new PlayerData(200L, "P01", ShipConfig.SHIP_TYPE_DELTAWING, false);
    }

    @Test
    public void testShouldCreateTwoBulletsForTwoDifferentPlayers() {
        // given
        PlayerData player2 = new PlayerData(300L, "P02", ShipConfig.SHIP_TYPE_DELTAWING, false);
        player2.setWeapon(new GatlingGun());
        player.setWeapon(new GatlingGun()); // setting this weapon for player
                                            // ship because it will create one
                                            // bullet
        pp.put(200L, player);
        pp.put(300L, player2);

        // when
        bp.addAmmo(player.getId());
        bp.addAmmo(player2.getId());

        // then
        Assert.assertEquals(bp.poolSize(), 2);
    }

    @Test
    public void testShouldCreateOneBullet_CooldownEffectActivated() {
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player
                                            // ship because it will create one
                                            // bullet
        pp.put(200L, player);

        // when
        bp.addAmmo(player.getId());
        bp.addAmmo(player.getId());

        // then
        Assert.assertEquals(bp.poolSize(), 1);
    }

    @Test
    public void testShouldCreateOneBullet() {
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player
                                            // ship because it will create one
                                            // bullet
        pp.put(200L, player);

        // when
        bp.addAmmo(player.getId());

        // then
        Assert.assertEquals(bp.poolSize(), 1);
    }

    @Test
    public void testShouldClearThePool() {
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player
                                            // ship because it will create one
                                            // bullet
        pp.put(10L, player);

        bp.addAmmo(10L);
        Assert.assertEquals(bp.poolSize(), 1);

        // get rid of waiting for player to be able to shoot again
        player.getWeapon().decreaseCooldownValue(player.getWeapon().getCooldown());

        bp.addAmmo(10L);
        Assert.assertEquals(bp.poolSize(), 2);

        bp.clear();
        Assert.assertEquals(bp.poolSize(), 0);
    }
}
