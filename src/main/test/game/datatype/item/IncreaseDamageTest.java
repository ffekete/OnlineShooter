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
    
    PlayerData player;

    @Test
    public void testShouldCreateInitDamage() {
        Assert.assertEquals(id.getName(), ItemType.INCREASE_DAMAGE.getVisibleName());
    }

    @Test
    public void testIncreaseDamageShouldIncreaseDamage() {
        AIDao aiDao = new AIDao(false, false);
        PlayerData player = new PlayerData(1L, "P01", ShipConfig.DELTAWING, aiDao);

        /* When */
        id.applyEffect(player);

        /* Then */
        Assert.assertEquals(player.getWeapon().getDamage(), player.getWeapon().getAmmoType().getDamage(1));
    }
}
