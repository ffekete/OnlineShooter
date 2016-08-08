package tests.items;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.datatypes.Items.IncreaseDamage;
import game.model.PlayerData;

public class IncreaseDamageTest {

    IncreaseDamage id = new IncreaseDamage();
    
    @BeforeMethod
    public void initPlayer(){
        
    }
    
    @Test 
    public void testShouldCreateInitDamage(){
        Assert.assertEquals(id.getName(), "Damage +1");
    }
    
    @Test
    public void testIncreaseDamageShouldIncreaseDamage(){
        PlayerData player = new PlayerData(1L, "P01", "Deltawing"); 
        
        /* Given*/
        long initDamage = player.getWeapon().getDamage();
        
        /* When */
        id.applyEffect(player);
        
        /* Then */
        Assert.assertEquals(player.getWeapon().getDamage(), initDamage + 1L);
    }
}
