package tests.shields;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.ShieldConfig;
import game.datatypes.shield.AtomShield;
import game.datatypes.shield.NormalShield;
import game.model.PlayerData;

public class NormalShieldTest {

    final NormalShield ns = new NormalShield();
    final AtomShield as = new AtomShield();
    
    @Test
    public void testShouldCreateShield(){
        Assert.assertEquals(ns.getName(), "Normal shield");
    }
    
    @Test
    public void testShouldIncreaseShield(){
        PlayerData player = new PlayerData(0L, "P05", "Interceptor");
        
        long initProtection = player.getShield().getProtection();
        long initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        
        ns.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.NORMAL_SHIELD_PROTECTION);
    }
    
    @Test
    public void testShouldIncreaseShieldAndSwitchBackToNormal(){
        PlayerData player = new PlayerData(0L, "P05", "Interceptor");
        
        long initProtection = player.getShield().getProtection();
        long initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        
        as.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.ATOM_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.ATOM_SHIELD_PROTECTION);
        
        ns.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.NORMAL_SHIELD_PROTECTION);
    }
}
