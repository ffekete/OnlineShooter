package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.GameConfig;
import game.config.constant.ItemType;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class IncreaseScoreTest {

    final IncreaseScore is = new IncreaseScore();

    @Test
    public void testShouldCreateIncreaseScore() {
        Assert.assertEquals(is.getName(), ItemType.INCREASE_SCORE.getVisibleName());
    }

    @Test
    public void testShouldIncreaseScore() {
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
        PlayerData player = new PlayerData(6L, "P00", "Interceptor", aiDao);

        long initScore = player.getScore();

        is.applyEffect(player);

        Assert.assertEquals(initScore + GameConfig.ITEM_SCORE_VALUE, player.getScore());
    }
}
