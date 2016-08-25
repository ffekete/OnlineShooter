package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ItemConfig;
import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;

public class IncreaseSpeedTest {

    final IncreaseSpeed is = new IncreaseSpeed();

    @Test
    public void testShouldCreateIncreaseSpeed() {
        Assert.assertEquals(is.getName(), SpawnableItemType.INCREASE_SPEED.getVisibleName());
    }

    @Test
    public void testShouldIncreaseSpeed() {
        PlayerData player = new PlayerData(3L, "P04", "Mercury", false);

        double initSpeed = player.getSpeed();

        is.applyEffect(player);

        Assert.assertEquals(initSpeed + ItemConfig.INCREASE_SPEED_VALUE, player.getSpeed());
    }
}
