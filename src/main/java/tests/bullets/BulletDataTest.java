package tests.bullets;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.datatypes.bullet.BulletData;

public class BulletDataTest {
	
	@Test
	public void testPhysicalRepresentation(){
		BulletData bullet = new BulletData(0.0, 0.0, 90.0, 0L, 5);
		
		Assert.assertEquals(bullet.getPhysicalRepresentation(), "{\"shape\": \"circle\", \"startx\": \"0.0\", \"starty\": \"0.0\", \"radius\" : \"15\"}");
	}
}
