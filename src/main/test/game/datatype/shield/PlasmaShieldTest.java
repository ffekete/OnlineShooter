package game.datatype.shield;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ItemType;
import game.config.constant.ShieldConfig;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class PlasmaShieldTest {

    final PlasmaShield ps = new PlasmaShield();

    @Test
    public void testShouldCreateShield() {
        Assert.assertEquals(ps.getName(), ItemType.PLASMA_SHIELD.getVisibleName());
    }

    @Test
    public void testShouldIncreaseShield() {
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
        PlayerData player = new PlayerData(0L, "P05", ShipConfig.INTERCEPTOR, aiDao);

        long initProtection = player.getShield().getProtection();
        long initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);

        ps.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.PLASMA_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.PLASMA_SHIELD_PROTECTION);
    }
}
