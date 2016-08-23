package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.datatype.PlayerData;
import game.datatype.item.IncreaseRateOfFire;

public class IncreaseRateOfFireTest {

    final IncreaseRateOfFire  irf = new IncreaseRateOfFire();
    
    @Test
    public void testShouldCreateIrf(){
        Assert.assertEquals(irf.getName(), "Rate of fire +1");
    }
    
    @Test
    public void testShouldIncreasePlayersRateOfFire(){
        PlayerData player = new PlayerData(3L, "P04", "Mercury", false);
        
        long initRateOfFire = player.getWeapon().getRateOfFire();
        
        irf.applyEffect(player);
        
        Assert.assertEquals(initRateOfFire - 1L, player.getWeapon().getRateOfFire());
    }
}
