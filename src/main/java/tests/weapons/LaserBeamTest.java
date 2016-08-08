package tests.weapons;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bullet.LaserBeam;
import config.WeaponConfig;
import factory.ShipFactory;
import interfaces.Ship;
import model.Coordinate;

public class LaserBeamTest{

	/* ************************************** Beam creation related tests ************************************* */
	@DataProvider(name = "angleSourceForY")
	public Object[][] angleSourceForY(){
		return new Object[][]{
		//   X     Y     angle  expectedXCoordinate
			{0.0D, 0.0D, 90.0D, 150.0D},
			{0.0D, 0.0D, 180.0D, 0.0D},
			{0.0D, 0.0D, 270.0D, -150.0D},
			{0.0D, 0.0D, 0.0D, 0.0D}
		};
	}
	
	@DataProvider(name = "angleSourceForX")
	public Object[][] angleSourceForX(){
		return new Object[][]{
		//   X     Y     angle  expectedYCoordinate
			{0.0D, 0.0D, 90.0D, 0.0D},
			{0.0D, 0.0D, 180.0D, -150.0D},
			{0.0D, 0.0D, 270.0D, 0.0D},
			{0.0D, 0.0D, 0.0D, 150.0D}
		};
	}
	
	@Test(dataProvider = "angleSourceForX")
	public void testLaserBeam_shouldCreateLaserBeam_XCoordinate(double x, double y, double angle, double expectedXCoordinate){
		LaserBeam laserBeam = new LaserBeam(x, y, angle, 0L, 5L);
		
		Coordinate endPoint = laserBeam.getEndPoint();
		
		Assert.assertEquals(endPoint.getX(), expectedXCoordinate, 0.00001D);
	}
	
	@Test(dataProvider = "angleSourceForY")
	public void testLaserBeam_shouldCreateLaserBeam_YCoordinate(double x, double y, double angle, double expectedYCoordinate){
		LaserBeam laserBeam = new LaserBeam(x, y, angle, 0L, 5L);
		
		Coordinate endPoint = laserBeam.getEndPoint();
		
		Assert.assertEquals(endPoint.getY(), expectedYCoordinate, 0.00001D);
	}
	
	/* *********************************** Beam hit related tests ********************************************* */
	@DataProvider(name="hitCheckerInputList")
	public Object[][] hitCheckerInputList(){
		return new Object[][]{
			{0.0D, 0.0D, 0.0d, -WeaponConfig.LASER_BEAM_HIT_RADIUS, 0.0d, true},
			{0.0D, 0.0D, 0.0d, WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS, 0.0d, true},
			{0.0D, 0.0D, 0.0d, (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d, WeaponConfig.LASER_BEAM_HIT_RADIUS, true},
			{0.0D, 0.0D, 0.0d, -1.0 - WeaponConfig.LASER_BEAM_HIT_RADIUS, 0.0d, false},
			{0.0D, 0.0D, 0.0d, WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d, 0.0d, false},
			{0.0D, 0.0D, 0.0d, (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d, WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d, false},
		};
	}
	
	@Test(dataProvider="hitCheckerInputList")
	public void testHitCheckerForLaserBeam(double beamStartX, double beamStartY, double angle, double objectX, double objectY, boolean expHitDetected){
		LaserBeam laserBeam = new LaserBeam(beamStartX, beamStartY, angle, 0, 0);
		
		Ship ship = ShipFactory.createShip("Interceptor");
		ship.setCoordinates(objectX, objectY);
		boolean hitDetected = laserBeam.hits(ship);
		Assert.assertEquals(hitDetected, expHitDetected);
	}
}
