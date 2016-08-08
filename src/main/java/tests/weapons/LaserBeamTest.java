package tests.weapons;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bullet.LaserBeam;
import model.Coordinate;

public class LaserBeamTest{

	@DataProvider(name = "angleSourceForY")
	public Object[][] angleSourceForY(){
		return new Object[][]{
			{0.0D, 0.0D, 90.0D, 150.0D},
			{0.0D, 0.0D, 180.0D, 0.0D},
			{0.0D, 0.0D, 270.0D, -150.0D},
			{0.0D, 0.0D, 0.0D, 0.0D}
		};
	}
	
	@DataProvider(name = "angleSourceForX")
	public Object[][] angleSourceForX(){
		return new Object[][]{
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
}
