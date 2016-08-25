package game.datatype.shield;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ShieldConfig;
import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;

public class AtomShieldTest {

    final AtomShield as = new AtomShield();

    @Test
    public void testShouldCreateShield() {
        Assert.assertEquals(as.getName(), SpawnableItemType.ATOM_SHIELD.getVisibleName());
    }

    @Test
    public void testShouldIncreaseShield() {
        PlayerData player = new PlayerData(0L, "P05", "Interceptor", false);

        long initProtection = player.getShield().getProtection();
        long initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);

        as.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.ATOM_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.ATOM_SHIELD_PROTECTION);
    }
}
