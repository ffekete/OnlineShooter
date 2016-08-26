package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ItemConfig;
import game.config.constant.ItemType;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class IncreaseManeuverabilityTest {

    IncreaseManeuverability im = new IncreaseManeuverability();

    @Test
    public void testShouldCreateIncreaseManeuverability() {
        Assert.assertEquals(im.getName(), ItemType.INCREASE_MANEUVERABILITY.getVisibleName());
    }

    @Test
    public void testShouldIncreaseManeuverability() {
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
        PlayerData player = new PlayerData(5L, "P01", ShipConfig.QUICKSILVER, aiDao);
        double initIm = player.getManeuverability();

        /* When */
        im.applyEffect(player);

        /* Then */
        Assert.assertEquals(initIm, player.getManeuverability() + ItemConfig.MANEUVERABILITY_INCREASE_VALE);
    }
}
