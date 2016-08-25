package game.datahandler;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import game.config.constant.SpawnableItemType;
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
		return new Object[][] {
			{ SpawnableItemType.GATLING_GUN, GatlingGun.class },
			{ SpawnableItemType.LASER_CANNON, LaserCannon.class },
			{ SpawnableItemType.DOUBLE_GATLING_GUN, DoubleGatlingGun.class },
			{ SpawnableItemType.SHOTGUN, Shotgun.class },
			{ SpawnableItemType.INCREASE_DAMAGE, IncreaseDamage.class },
			{ SpawnableItemType.INCRASE_RATE_OF_FIRE, IncreaseRateOfFire.class },
			{ SpawnableItemType.INCREASE_MANEUVERABILITY, IncreaseManeuverability.class },
			{ SpawnableItemType.INCREASE_SPEED, IncreaseSpeed.class },
			{ SpawnableItemType.ATOM_SHIELD, AtomShield.class },
			{ SpawnableItemType.PLASMA_SHIELD, PlasmaShield.class },
			{ SpawnableItemType.HEALTH_PACK, HealthPack.class },
			{ SpawnableItemType.INCREASE_SCORE, IncreaseScore.class }};
	}

	@Test(dataProvider = "itemTypes")
	public void testItemCreation(SpawnableItemType type, Class<? extends SpawnableItem> clazz) {
		// when
		SpawnableItem result = itemCreationHandler.createItem(type);
		System.out.println(result.getClass());
		// then
		Assert.assertTrue(result.getClass() == clazz);
	}
}
