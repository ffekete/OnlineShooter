package game.datatype.item;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;
import game.datatype.item.IncreaseDamage;

public class IncreaseDamageTest {

    IncreaseDamage id = new IncreaseDamage();
    
    PlayerData player;

    @BeforeMethod
    public void initPlayer() {
    	player = new PlayerData(1L, "P01", "Deltawing", false);
    }

    @Test
    public void testShouldCreateInitDamage() {
        Assert.assertEquals(id.getName(), SpawnableItemType.INCREASE_DAMAGE.getVisibleName());
    }

    @Test
    public void testIncreaseDamageShouldIncreaseDamage() {

        /* When */
        id.applyEffect(player);

        /* Then */
        Assert.assertEquals(player.getWeapon().getDamage(), player.getWeapon().getAmmoType().getDamage(1));
    }
}
