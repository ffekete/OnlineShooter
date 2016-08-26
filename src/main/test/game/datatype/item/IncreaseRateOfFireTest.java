package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ItemType;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class IncreaseRateOfFireTest {

    final IncreaseRateOfFire irf = new IncreaseRateOfFire();

    @Test
    public void testShouldCreateIrf() {
        Assert.assertEquals(irf.getName(), ItemType.INCRASE_RATE_OF_FIRE.getVisibleName());
    }

    @Test
    public void testShouldIncreasePlayersRateOfFire() {
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
        PlayerData player = new PlayerData(3L, "P04", ShipConfig.MERCURY, aiDao);

        long initRateOfFire = player.getWeapon().getRateOfFire();

        irf.applyEffect(player);

        Assert.assertEquals(initRateOfFire - 1L, player.getWeapon().getRateOfFire());
    }
}
