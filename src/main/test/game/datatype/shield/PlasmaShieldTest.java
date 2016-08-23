package game.datatype.shield;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ShieldConfig;
import game.datatype.PlayerData;
import game.datatype.shield.PlasmaShield;

public class PlasmaShieldTest {

    final PlasmaShield ps = new PlasmaShield();
    
    @Test
    public void testShouldCreateShield(){
        Assert.assertEquals(ps.getName(), "Plasma shield");
    }
    
    @Test
    public void testShouldIncreaseShield(){
        PlayerData player = new PlayerData(0L, "P05", "Interceptor", false);
        
        long initProtection = player.getShield().getProtection();
        long initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        
        ps.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.PLASMA_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.PLASMA_SHIELD_PROTECTION);
    }
}
