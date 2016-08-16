package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.datahandler.BulletPool;
import game.datahandler.PlayerPool;
import game.datatypes.PlayerData;
import game.datatypes.weapons.GatlingGun;
import game.entrypoint.Application;

@ContextConfiguration(classes={Application.class})
public class BulletPoolTest extends AbstractTestNGSpringContextTests{

    @Autowired
    PlayerPool pp;
    
    @Autowired BulletPool bp;
    
    PlayerData player;
    
    @BeforeMethod
    public void initBulletPool(){
        bp.clearPool();
        player = new PlayerData(200L, "P01", "Deltawing");
    }
    
    @Test
    public void testShouldCreateOneBullet(){
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player ship because it will create one bullet
        pp.getPool().put(200L, player);
        
        // when
        bp.addBullet(player.getId());
        
        // then
        Assert.assertEquals(bp.getBulletPool().size(), 1);
    }
}
