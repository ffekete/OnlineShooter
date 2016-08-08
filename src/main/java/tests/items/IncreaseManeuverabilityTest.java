package tests.items;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.ItemConfig;
import game.datatypes.Items.IncreaseMAneuverability;
import game.model.PlayerData;

public class IncreaseManeuverabilityTest {

	IncreaseMAneuverability im = new IncreaseMAneuverability();
	
	@Test
	public void testShouldCreateIncreaseManeuverability(){
		Assert.assertEquals(im.getName(), "Maneuverability +1");
	}
	
	@Test
	public void testShouldIncreaseManeuverability(){
		PlayerData player = new PlayerData(5L, "P01", "Quicksilver");
		double initIm = player.getManeuverability();
		
		/* When */
		im.applyEffect(player);
		
		/* Then */
		Assert.assertEquals(initIm, player.getManeuverability() + ItemConfig.MANEUVERABILITY_INCREASE_VALE);
	}
}
