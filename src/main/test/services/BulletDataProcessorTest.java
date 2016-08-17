package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import game.config.GameConfig;
import game.datahandler.BulletPool;
import game.datahandler.PlayerPool;
import game.datatypes.PlayerData;
import game.datatypes.bullet.BulletData;
import game.datatypes.weapons.GatlingGun;
import game.entrypoint.Application;
import game.interfaces.Bullet;
import game.interfaces.BulletDataProcessorInterface;
import game.interfaces.BulletPoolList;

@ContextConfiguration(classes=Application.class)
public class BulletDataProcessorTest extends AbstractTestNGSpringContextTests{

    @Autowired
    PlayerPool pp;
    
    @Autowired
    BulletDataProcessorInterface bdp;
    
    @Autowired BulletPoolList<Bullet> bp;
    
    PlayerData player;
    
    @BeforeMethod
    public void initTests(){
        bp.clear();
        player = new PlayerData(200L, "P01", "Deltawing");
    }
    
    @DataProvider(name = "bulletDataInput")
    Object[][] bulletDataInput(){
        return new Object[][]{
        //   x,y,angle,number of cycles to count, expectedX, expectedY
             {0.0d, 0.0d, 0.0d, 1, GameConfig.BULLET_INITIAL_SPEED, 0.0D},
             {0.0d, 0.0d, 0.0d, 3, 3 * GameConfig.BULLET_INITIAL_SPEED, 0.0D},
             {GameConfig.STAGE_POS_LIMIT_X, 0.0d, 0.0d, 1, GameConfig.STAGE_NEG_LIMIT_X + GameConfig.BULLET_INITIAL_SPEED, 0.0D},
             {GameConfig.STAGE_NEG_LIMIT_X, 0.0d, 180.0d, 1, GameConfig.STAGE_POS_LIMIT_X - GameConfig.BULLET_INITIAL_SPEED, 0.0D},
        };
    }
    
    @Test(dataProvider = "bulletDataInput")
    public void testShouldUpdateBulletCoordinates(double x, double y, double angle, int cycles, double expectedX, double expectedY){
        // given
        player.setWeapon(new GatlingGun()); // setting this weapon for player ship because it will create one bullet
        player.setX(x);
        player.setY(y);
        player.setShipAngle(angle);
        pp.put(200L, player);
        bp.addBullet(player.getId());

        // when
        for(int i = 1; i <= cycles; i++){
            bdp.updateBulletData();
        }
        
        BulletData bullet = null;
        bullet = (BulletData) bp.getNthBullet(0);
        
        double bulletX = bullet.getX();
        double bulletY = bullet.getY();
        
        // then
        
        Assert.assertNotNull(bullet);
        Assert.assertEquals(bulletX, expectedX,0.0001, "Expected and calculated X coordinates does not match! (expected: " + expectedX + ", calculated: " + bulletX + ")");
        Assert.assertEquals(bulletY, expectedY,0.0001, "Expected and calculated Y coordinates does not match! (expected: " + expectedY + ", calculated: " + bulletY + ")");
    }
}
