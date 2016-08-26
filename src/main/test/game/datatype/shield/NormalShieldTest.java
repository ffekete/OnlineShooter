package game.datatype.shield;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.ShieldConfig;
import game.config.constant.ItemType;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class NormalShieldTest {

    final NormalShield ns = new NormalShield();
    final AtomShield as = new AtomShield();

    public AIDao aiDao;

    @BeforeMethod
    public void init() {
        aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
    }

    @Test
    public void testShouldCreateShield() {
        Assert.assertEquals(ns.getName(), ItemType.NORMAL_SHIELD.getVisibleName());
    }

    @Test
    public void testShouldIncreaseShield() {

        PlayerData player = new PlayerData(0L, "P05", "Interceptor", aiDao);

        long initProtection = player.getShield().getProtection();
        long initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);

        ns.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.NORMAL_SHIELD_PROTECTION);
    }

    @Test
    public void testShouldIncreaseShieldAndPowerUp() {
        PlayerData player = new PlayerData(0L, "P05", "Interceptor", aiDao);

        long initProtection = player.getShield().getProtection();
        long initMaxProtection = player.getShield().getMaxProtectionValue();
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
