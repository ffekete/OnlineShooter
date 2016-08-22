package game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.datatype.PlayerData;
import game.datatype.weapon.GatlingGun;
import game.entrypoint.Application;
import game.interfaces.Bullet;
import game.interfaces.BulletPoolList;
import game.interfaces.PlayerPoolMap;

@ContextConfiguration(classes={Application.class})
public class BulletPoolTest extends AbstractTestNGSpringContextTests{

    @Autowired
    PlayerPoolMap<Long, PlayerData> pp;
    
    @Autowired BulletPoolList<Bullet> bp;
    
    PlayerData player;
    
    @BeforeMethod
    public void initBulletPool(){
        bp.clear();
        player = new PlayerData(200L, "P01", "Deltawing", false);
    }
    
    @Test
    public void testShouldCreateOneBullet(){
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player ship because it will create one bullet
        pp.put(200L, player);
        
        // when
        bp.addBullet(player.getId());
        
        // then
        Assert.assertEquals(bp.poolSize(), 1);
    }
    
    @Test
    public void testShouldClearThePool(){
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player ship because it will create one bullet
        pp.put(10L, player);
             
        bp.addBullet(10L);
        Assert.assertEquals(bp.poolSize(), 1);
        
        // get rid of waiting for player to be able to shoot again
        player.getWeapon().decreaseRateOfFireCooldownValue(player.getWeapon().getRateOfFireCooldown());
        
        bp.addBullet(10L);
        Assert.assertEquals(bp.poolSize(), 2);
        
        bp.clear();
        Assert.assertEquals(bp.poolSize(), 0);
    }
}
