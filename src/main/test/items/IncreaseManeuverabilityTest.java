package items;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constants.ItemConfig;
import game.datatypes.PlayerData;
import game.datatypes.Items.IncreaseMAneuverability;

public class IncreaseManeuverabilityTest {

    IncreaseMAneuverability im = new IncreaseMAneuverability();
    
    @Test
    public void testShouldCreateIncreaseManeuverability(){
        Assert.assertEquals(im.getName(), "Maneuverability +1");
    }
    
    @Test
    public void testShouldIncreaseManeuverability(){
        PlayerData player = new PlayerData(5L, "P01", "Quicksilver");
        double initIm = player.getManeuverability();
        
        /* When */
        im.applyEffect(player);
        
        /* Then */
        Assert.assertEquals(initIm, player.getManeuverability() + ItemConfig.MANEUVERABILITY_INCREASE_VALE);
    }
}
