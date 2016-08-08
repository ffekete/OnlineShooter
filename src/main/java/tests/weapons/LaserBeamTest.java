package tests.weapons;

import org.testng.Assert;
import org.testng.annotations.Test;

import bullet.LaserBeam;
import model.Coordinate;

public class LaserBeamTest{
	
	@Test
	public void testLaserBeam_shouldCreateLaserBeam_90Angle_XEqualsTo_0(){
		LaserBeam laserBeam = new LaserBeam(0.0D, 0.0D, 90.0D, 0L, 5L);
		
		Coordinate endPoint = laserBeam.getEndPoint();
		
		Assert.assertEquals(endPoint.getX(), 0.0D, 0.00001D);
	}
	
	@Test
	public void testLaserBeam_shouldCreateLaserBeam_90angle_YEqualsTo_minus150(){
		LaserBeam laserBeam = new LaserBeam(0.0D, 0.0D, 90.0D, 0L, 5L);
		
		Coordinate endPoint = laserBeam.getEndPoint();
		
		Assert.assertEquals(endPoint.getY(), 150.0D, 0.00001D);
	}
	
	@Test
	public void testLaserBeam_shouldCreateLaserBeam_0Angle_YEqualsTo_0(){
		LaserBeam laserBeam = new LaserBeam(0.0D, 0.0D, 0.0D, 0L, 5L);
		
		Coordinate endPoint = laserBeam.getEndPoint();
		
		Assert.assertEquals(endPoint.getY(), 0.0D, 0.00001D);
	}
	
	@Test
	public void testLaserBeam_shouldCreateLaserBeam_0Angle_XEqualsTo_150(){
		LaserBeam laserBeam = new LaserBeam(0.0D, 0.0D, 0.0D, 0L, 5L);
		
		Coordinate endPoint = laserBeam.getEndPoint();
		
		Assert.assertEquals(endPoint.getX(), 150.0D, 0.00001D);
	}
	
}
