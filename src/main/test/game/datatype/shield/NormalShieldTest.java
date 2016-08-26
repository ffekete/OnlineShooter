package game.datatype.shield;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ShieldConfig;
import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;

public class NormalShieldTest {

    final NormalShield ns = new NormalShield();
    final AtomShield as = new AtomShield();

    @Test
    public void testShouldCreateShield() {
        Assert.assertEquals(ns.getName(), SpawnableItemType.NORMAL_SHIELD.getVisibleName());
    }

    @Test
    public void testShouldIncreaseShield() {
        PlayerData player = new PlayerData(0L, "P05", "Interceptor", false);

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
        PlayerData player = new PlayerData(0L, "P05", "Interceptor", false);

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
