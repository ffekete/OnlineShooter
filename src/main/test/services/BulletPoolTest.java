package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cofiguration.TestContextConfiguration;
import game.datahandler.BulletPool;
import game.datahandler.PlayerPool;
import game.datatypes.PlayerData;
import game.datatypes.weapons.GatlingGun;

@ContextConfiguration(classes={TestContextConfiguration.class})
public class BulletPoolTest extends AbstractTestNGSpringContextTests{

	@Autowired
	PlayerPool pp;
	
	@Autowired BulletPool bp;
	
	@Autowired
	PlayerData player;
	
	@BeforeMethod
	public void initBulletPool(){
		bp.clearPool();
	}
	
	@Test
	public void testShouldCreateOneBullet(){
		// given
		player.setWeapon(new GatlingGun()); // setting this weapon for player ship because it will create one bullet
		pp.getPool().put(200L, player);
		
		// when
		bp.addBullet(player.getId());
		
		// then
		Assert.assertEquals(bp.getBulletPool().size(), 1);
	}
}
