package items;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constants.ShipConfig;
import game.datatypes.PlayerData;
import game.datatypes.Items.HealthPack;

public class HealthPackTest {

    PlayerData player;
    HealthPack hp = new HealthPack();    
    
    @BeforeMethod
    public void initFields(){
        player = new PlayerData(0L, "P01", "Interceptor");
    }
    
    @Test
    public void testCreateHealthPackShouldCreate(){
        Assert.assertEquals(hp.getName(), "Health +5");
    }
    
    @Test
    public void testHealthpackShouldIncreasePlayerHpBy5(){
        player.setHp(0L);
        
        hp.applyEffect(player);
        
        Assert.assertEquals(player.getHp(), (Long)(5L));
    }
    
    @Test
    public void testHealthpackShouldNotIncreaseBeyondLimit(){
        player.setHp(ShipConfig.INTERCEPTOR_INIT_HP - 2);

        hp.applyEffect(player);
        
        Assert.assertEquals(player.getHp(), (Long)ShipConfig.INTERCEPTOR_INIT_HP);
    }
}
