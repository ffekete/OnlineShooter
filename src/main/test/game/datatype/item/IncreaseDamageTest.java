package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.ItemType;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class IncreaseDamageTest {

    IncreaseDamage id = new IncreaseDamage();

    @BeforeMethod
    public void initPlayer() {

    }

    @Test
    public void testShouldCreateInitDamage() {
        Assert.assertEquals(id.getName(), ItemType.INCREASE_DAMAGE.getVisibleName());
    }

    @Test
    public void testIncreaseDamageShouldIncreaseDamage() {
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
        PlayerData player = new PlayerData(1L, "P01", ShipConfig.DELTAWING, aiDao);

        /* Given */
        long initDamage = player.getWeapon().getDamage();

        /* When */
        id.applyEffect(player);

        /* Then */
        Assert.assertEquals(player.getWeapon().getDamage(), initDamage + 1L);
    }
}
