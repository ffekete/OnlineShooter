package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;

public class IncreaseRateOfFireTest {

    final IncreaseRateOfFire irf = new IncreaseRateOfFire();

    @Test
    public void testShouldCreateIrf() {
        Assert.assertEquals(irf.getName(), SpawnableItemType.INCRASE_RATE_OF_FIRE.getVisibleName());
    }

    @Test
    public void testShouldIncreasePlayersRateOfFire() {
        PlayerData player = new PlayerData(3L, "P04", "Mercury", false);

        long initRateOfFire = player.getWeapon().getRateOfFire();

        irf.applyEffect(player);

        Assert.assertEquals(initRateOfFire - 1L, player.getWeapon().getRateOfFire());
    }
}
