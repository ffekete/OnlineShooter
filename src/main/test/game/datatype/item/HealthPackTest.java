package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.ShipConfig;
import game.config.constant.ItemType;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class HealthPackTest {

    PlayerData player;
    HealthPack hp = new HealthPack();

    @BeforeMethod
    public void initFields() {
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
        player = new PlayerData(0L, "P01", "Interceptor", aiDao);

    }

    @Test
    public void testCreateHealthPackShouldCreate() {
        Assert.assertEquals(hp.getName(), ItemType.HEALTH_PACK.getVisibleName());
    }

    @Test
    public void testHealthpackShouldIncreasePlayerHpBy5() {
        player.setHp(0L);

        hp.applyEffect(player);

        Assert.assertEquals(player.getHp(), (Long) (5L));
    }

    @Test
    public void testHealthpackShouldNotIncreaseBeyondLimit() {
        player.setHp(ShipConfig.INTERCEPTOR_MAX_HP - 2);

        hp.applyEffect(player);

        Assert.assertEquals(player.getHp(), (Long) ShipConfig.INTERCEPTOR_MAX_HP);
    }
}
