package tests.shields;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.ShieldConfig;
import game.datatypes.PlayerData;
import game.datatypes.shield.PlasmaShield;

public class PlasmaShieldTest {

    final PlasmaShield ps = new PlasmaShield();
    
    @Test
    public void testShouldCreateShield(){
        Assert.assertEquals(ps.getName(), "Plasma shield");
    }
    
    @Test
    public void testShouldIncreaseShield(){
        PlayerData player = new PlayerData(0L, "P05", "Interceptor");
        
        long initProtection = player.getShield().getProtection();
        long initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        
        ps.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.PLASMA_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.PLASMA_SHIELD_PROTECTION);
    }
}
