package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
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
        AIDao aiDao = new AIDao(false, false);
        PlayerData player = new PlayerData(3L, "P04", ShipConfig.MERCURY, aiDao);

        double initRateOfFire = player.getWeapon().getRateOfFire();

        irf.applyEffect(player);

        Assert.assertEquals(initRateOfFire + WeaponConfig.GATLING_GUN.getInitRateOfFire(),
                player.getWeapon().getRateOfFire());
    }
}
