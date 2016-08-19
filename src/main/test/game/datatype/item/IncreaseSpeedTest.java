package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ItemConfig;
import game.datatype.PlayerData;
import game.datatype.item.IncreaseSpeed;

public class IncreaseSpeedTest {
    
    final IncreaseSpeed is = new IncreaseSpeed();
    
    @Test
    public void testShouldCreateIncreaseSpeed(){
        Assert.assertEquals(is.getName(), "Speed +1");
    }
    
    @Test
    public void testShouldIncreaseSpeed(){
        PlayerData player = new PlayerData(3L, "P04", "Mercury");
        
        double initSpeed = player.getSpeed();
        
        is.applyEffect(player);
        
        Assert.assertEquals(initSpeed + ItemConfig.INCREASE_SPEED_VALUE, player.getSpeed());
    }
}
