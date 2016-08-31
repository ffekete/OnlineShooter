package game.datahandler;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import game.config.constant.ItemType;
import game.datatype.item.HealthPack;
import game.datatype.item.IncreaseDamage;
import game.datatype.item.IncreaseManeuverability;
import game.datatype.item.IncreaseRateOfFire;
import game.datatype.item.IncreaseScore;
import game.datatype.item.IncreaseSpeed;
import game.datatype.shield.AtomShield;
import game.datatype.shield.PlasmaShield;
import game.datatype.weapon.DoubleGatlingGun;
import game.datatype.weapon.GatlingGun;
import game.datatype.weapon.LaserCannon;
import game.datatype.weapon.Shotgun;
import game.interfaces.SpawnableItem;

public class ItemCreationHandlerTest {

    private ItemCreationHandler itemCreationHandler;

    @BeforeTest
    public void initialize() {
        // given
        itemCreationHandler = new ItemCreationHandler();
    }

    @DataProvider(name = "itemTypes")
    public Object[][] itemTypes() {
        return new Object[][] { { ItemType.GATLING_GUN, GatlingGun.class },
                { ItemType.LASER_CANNON, LaserCannon.class },
                { ItemType.DOUBLE_GATLING_GUN, DoubleGatlingGun.class },
                { ItemType.SHOTGUN, Shotgun.class },
                { ItemType.INCREASE_DAMAGE, IncreaseDamage.class },
                { ItemType.INCRASE_RATE_OF_FIRE, IncreaseRateOfFire.class },
                { ItemType.INCREASE_MANEUVERABILITY, IncreaseManeuverability.class },
                { ItemType.INCREASE_SPEED, IncreaseSpeed.class },
                { ItemType.ATOM_SHIELD, AtomShield.class },
                { ItemType.PLASMA_SHIELD, PlasmaShield.class },
                { ItemType.HEALTH_PACK, HealthPack.class },
                { ItemType.INCREASE_SCORE, IncreaseScore.class } };
    }

    @Test(dataProvider = "itemTypes")
    public void testItemCreation(ItemType type, Class<? extends SpawnableItem> clazz) {
        // when
        SpawnableItem result = itemCreationHandler.createItem(type);
        // then
        Assert.assertTrue(result.getClass() == clazz);
    }
}
