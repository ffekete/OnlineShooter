package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ItemConfig;
import game.config.constant.ItemType;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class IncreaseSpeedTest {

    final IncreaseSpeed is = new IncreaseSpeed();

    @Test
    public void testShouldCreateIncreaseSpeed() {
        Assert.assertEquals(is.getName(), ItemType.INCREASE_SPEED.getVisibleName());
    }

    @Test
    public void testShouldIncreaseSpeed() {
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
        PlayerData player = new PlayerData(3L, "P04", ShipConfig.MERCURY, aiDao);

        double initSpeed = player.getSpeed();

        is.applyEffect(player);

        Assert.assertEquals(initSpeed + ItemConfig.INCREASE_SPEED_VALUE, player.getSpeed());
    }
}
