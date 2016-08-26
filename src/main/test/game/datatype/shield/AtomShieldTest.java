package game.datatype.shield;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ItemType;
import game.config.constant.ShieldConfig;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class AtomShieldTest {

    final AtomShield as = new AtomShield();

    @Test
    public void testShouldCreateShield() {
        Assert.assertEquals(as.getName(), ItemType.ATOM_SHIELD.getVisibleName());
    }

    @Test
    public void testShouldIncreaseShield() {
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
        PlayerData player = new PlayerData(0L, "P05", ShipConfig.INTERCEPTOR, aiDao);

        double initProtection = player.getShield().getProtection();
        double initMaxProtection = player.getShield().getMaxProtectionValue();
        Assert.assertEquals(initProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);
        Assert.assertEquals(initMaxProtection, ShieldConfig.NORMAL_SHIELD_PROTECTION);

        as.applyEffect(player);
        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.ATOM_SHIELD_PROTECTION);
        Assert.assertEquals(player.getShield().getMaxProtectionValue(), ShieldConfig.ATOM_SHIELD_PROTECTION);
    }
}
