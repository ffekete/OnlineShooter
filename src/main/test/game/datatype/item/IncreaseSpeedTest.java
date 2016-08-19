package items;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constants.ItemConfig;
import game.datatypes.PlayerData;
import game.datatypes.Items.IncreaseSpeed;

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
