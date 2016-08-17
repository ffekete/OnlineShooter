package bullets;

import java.awt.geom.Point2D;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import factory.ShipFactory;
import game.config.WeaponConfig;
import game.datatypes.bullet.LaserBeam;
import game.interfaces.Ship;

public class LaserBeamTest {

    /*
     * ************************************** Beam creation related tests
     * *************************************
     */
    @DataProvider(name = "angleSourceForY")
    public Object[][] angleSourceForY() {
        return new Object[][] {
                // X Y angle expectedXCoordinate
                { 0.0D, 0.0D, 90.0D, WeaponConfig.LASER_BEAM_LENGTH }, { 0.0D, 0.0D, 180.0D, 0.0D },
                { 0.0D, 0.0D, 270.0D, -1.0 * WeaponConfig.LASER_BEAM_LENGTH }, { 0.0D, 0.0D, 0.0D, 0.0D } };
    }

    @DataProvider(name = "angleSourceForX")
    public Object[][] angleSourceForX() {
        return new Object[][] {
                // X Y angle expectedYCoordinate
                { 0.0D, 0.0D, 90.0D, 0.0D }, { 0.0D, 0.0D, 180.0D, -1.0 * WeaponConfig.LASER_BEAM_LENGTH },
                { 0.0D, 0.0D, 270.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, WeaponConfig.LASER_BEAM_LENGTH } };
    }

    @Test(dataProvider = "angleSourceForX")
    public void testLaserBeam_shouldCreateLaserBeam_XCoordinate(double x, double y, double angle,
            double expectedXCoordinate) {
        LaserBeam laserBeam = new LaserBeam(new Point2D.Double(x, y), angle, 0L, 5L);

        Point2D endPoint = laserBeam.getEndPoint();

        Assert.assertEquals(endPoint.getX(), expectedXCoordinate, 0.00001D);
    }

    @Test(dataProvider = "angleSourceForY")
    public void testLaserBeam_shouldCreateLaserBeam_YCoordinate(double x, double y, double angle,
            double expectedYCoordinate) {
        LaserBeam laserBeam = new LaserBeam(new Point2D.Double(x, y), angle, 0L, 5L);

        Point2D endPoint = laserBeam.getEndPoint();

        Assert.assertEquals(endPoint.getY(), expectedYCoordinate, 0.00001D);
    }

    /*
     * *********************************** Beam hit related tests
     * *********************************************
     */
    @DataProvider(name = "hitCheckerInputList")
    public Object[][] hitCheckerInputList() {
        return new Object[][] {
                /* hit, 0 degree */
                { 0.0D, 0.0D, 0.0d, -WeaponConfig.LASER_BEAM_HIT_RADIUS, 0.0d, true },
                { 0.0D, 0.0D, 0.0d, WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS, 0.0d, true },
                { 0.0D, 0.0D, 0.0d, (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d,
                        WeaponConfig.LASER_BEAM_HIT_RADIUS, true },
                { 0.0D, 0.0D, 0.0d, (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d,
                        -WeaponConfig.LASER_BEAM_HIT_RADIUS, true },

                /* hit, 90 degree */
                { 0.0D, 0.0D, 90.0d, 0.0d, -WeaponConfig.LASER_BEAM_HIT_RADIUS, true },
                { 0.0D, 0.0D, 90.0d, 0.0d, WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS, true },
                { 0.0D, 0.0D, 90.0d, WeaponConfig.LASER_BEAM_HIT_RADIUS,
                        (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d, true },
                { 0.0D, 0.0D, 90.0d, -WeaponConfig.LASER_BEAM_HIT_RADIUS,
                        (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d, true },

                /* hit, 180 degree */
                { 0.0D, 0.0D, 180.0d, WeaponConfig.LASER_BEAM_HIT_RADIUS, 0.0d, true },
                { 0.0D, 0.0D, 180.0d, -WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS, 0.0d,
                        true },
                { 0.0D, 0.0D, 180.0d, -(WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d,
                        -WeaponConfig.LASER_BEAM_HIT_RADIUS, true },
                { 0.0D, 0.0D, 180.0d, -(WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d,
                        WeaponConfig.LASER_BEAM_HIT_RADIUS, true },

                /* hit, 270 degree */
                { 0.0D, 0.0D, 270.0d, 0.0d, WeaponConfig.LASER_BEAM_HIT_RADIUS, true },
                { 0.0D, 0.0D, 270.0d, 0.0d, -(WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS),
                        true },
                { 0.0D, 0.0D, 270.0d, WeaponConfig.LASER_BEAM_HIT_RADIUS - 0.001d,
                        -((WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d), true },
                { 0.0D, 0.0D, 270.0d, -WeaponConfig.LASER_BEAM_HIT_RADIUS,
                        -((WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d), true },

                /* no hit, 0 degree */
                { 0.0D, 0.0D, 0.0d, -1.0 - WeaponConfig.LASER_BEAM_HIT_RADIUS, 0.0d, false },
                { 0.0D, 0.0D, 0.0d, WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d, 0.0d,
                        false },
                { 0.0D, 0.0D, 0.0d, (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d,
                        WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d, false },
                { 0.0D, 0.0D, 0.0d, (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d,
                        -WeaponConfig.LASER_BEAM_HIT_RADIUS - 1.0d, false },

                /* no hit, 90 degree */
                { 0.0D, 0.0D, 90.0d, 0.0d, -1.0 - WeaponConfig.LASER_BEAM_HIT_RADIUS, false },
                { 0.0D, 0.0D, 90.0d, 0.0d, WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d,
                        false },
                { 0.0D, 0.0D, 90.0d, WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d,
                        (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d, false },
                { 0.0D, 0.0D, 90.0d, -WeaponConfig.LASER_BEAM_HIT_RADIUS - 1.0d,
                        (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d, false },

                /* no hit, 180 degree */
                { 0.0D, 0.0D, 180.0d, 1.0 + WeaponConfig.LASER_BEAM_HIT_RADIUS, 0.0d, false },
                { 0.0D, 0.0D, 180.0d,
                        -1.0 * (WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d), 0.0d,
                        false },
                { 0.0D, 0.0D, 180.0d, -((WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d),
                        WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d, false },
                { 0.0D, 0.0D, 180.0d, -((WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d),
                        -WeaponConfig.LASER_BEAM_HIT_RADIUS - 1.0d, false },

                /* no hit, 270 degree */
                { 0.0D, 0.0D, 270.0d, 0.0d, -(-1.0 - WeaponConfig.LASER_BEAM_HIT_RADIUS), false },
                { 0.0D, 0.0D, 270.0d, 0.0d,
                        -(WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d), false },
                { 0.0D, 0.0D, 270.0d, WeaponConfig.LASER_BEAM_HIT_RADIUS + 1.0d,
                        -((WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d), false },
                { 0.0D, 0.0D, 270.0d, -WeaponConfig.LASER_BEAM_HIT_RADIUS - 1.0d,
                        -((WeaponConfig.LASER_BEAM_LENGTH + WeaponConfig.LASER_BEAM_HIT_RADIUS) / 2.0d), false }, };
    }

    @Test(dataProvider = "hitCheckerInputList")
    public void testHitCheckerForLaserBeam(double beamStartX, double beamStartY, double angle, double objectX,
            double objectY, boolean expHitDetected) {
        LaserBeam laserBeam = new LaserBeam(new Point2D.Double(beamStartX, beamStartY), angle, 0, 0);

        Ship ship = ShipFactory.createShip("Interceptor");
        ship.setCoordinate(objectX, objectY);
        boolean hitDetected = laserBeam.hits(ship);
        Assert.assertEquals(hitDetected, expHitDetected);
    }

    @Test
    public void testPhysicalRepresentation() {

        LaserBeam laserBeam = new LaserBeam(new Point2D.Double(0.0, 0.0), 0.0, 0, 0);

        Assert.assertEquals(laserBeam.getPhysicalRepresentation(),
                "{\"shape\" : \"line\", \"startx\": \"0.0\", \"starty\" :\"0.0\", \"endx\": \"1000.0\", \"endy\": \"0.0\"}");
    }

}
