package game.datatype.shield;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.ItemType;
import game.config.constant.ShieldConfig;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class NormalShieldTest {

    final NormalShield ns = new NormalShield();
    final AtomShield as = new AtomShield();

    public AIDao aiDao;

    public PlayerData player;

    @BeforeMethod
    public void init() {
        aiDao = new AIDao(false, false);
        player = new PlayerData(0L, "P05", ShipConfig.INTERCEPTOR, aiDao);
    }

    @Test
    public void testShouldCreateShield() {
        Assert.assertEquals(ns.getName(), ItemType.NORMAL_SHIELD.getVisibleName());
    }

    @Test
    public void testShouldIncreaseShield() {
        double initProtection = player.getShield().getProtection();
        double initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);

        ns.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.NORMAL_SHIELD_PROTECTION);
    }

    @Test
    public void testShouldIncreaseShieldAndPowerUp() {
        double initProtection = player.getShield().getProtection();
        double initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);

        as.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.ATOM_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.ATOM_SHIELD_PROTECTION);

        player.getShield().decreaseProtection(15);
        ns.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), 5 + ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.ATOM_SHIELD_PROTECTION);
    }
}
