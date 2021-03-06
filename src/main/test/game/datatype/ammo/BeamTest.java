package game.datatype.ammo;

import java.awt.geom.Point2D;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import factory.ShipFactory;
import game.config.constant.AmmoType;
import game.config.constant.ShipConfig;
import game.interfaces.Ship;

public class BeamTest {

    /*
     * ************************************** Beam creation related tests
     * *************************************
     */
    @DataProvider(name = "angleSourceForY")
    public Object[][] angleSourceForY() {
        return new Object[][] {
                // X Y angle expectedXCoordinate
                { 0.0D, 0.0D, 90.0D, AmmoType.LASER_BEAM.getLength() }, { 0.0D, 0.0D, 180.0D, 0.0D },
                { 0.0D, 0.0D, 270.0D, -1.0 * AmmoType.LASER_BEAM.getLength() }, { 0.0D, 0.0D, 0.0D, 0.0D } };
    }

    @DataProvider(name = "angleSourceForX")
    public Object[][] angleSourceForX() {
        return new Object[][] {
                // X Y angle expectedYCoordinate
                { 0.0D, 0.0D, 90.0D, 0.0D }, { 0.0D, 0.0D, 180.0D, -1.0 * AmmoType.LASER_BEAM.getLength() },
                { 0.0D, 0.0D, 270.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, AmmoType.LASER_BEAM.getLength() } };
    }

    @Test(dataProvider = "angleSourceForX")
    public void testLaserBeam_shouldCreateLaserBeam_XCoordinate(double x, double y, double angle,
            double expectedXCoordinate) {
        Beam laserBeam = new Beam(new Point2D.Double(x, y), angle, 0, 0);

        Point2D endPoint = laserBeam.getEndPoint();

        Assert.assertEquals(endPoint.getX(), expectedXCoordinate, 0.00001D);
    }

    @Test(dataProvider = "angleSourceForY")
    public void testLaserBeam_shouldCreateLaserBeam_YCoordinate(double x, double y, double angle,
            double expectedYCoordinate) {
        Beam laserBeam = new Beam(new Point2D.Double(x, y), angle, 0, 0);

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
                { 0.0D, 0.0D, 0.0d, -AmmoType.LASER_BEAM.getInitHitRadius(), 0.0d, true },
                { 0.0D, 0.0D, 0.0d, AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius(), 0.0d,
                        true },
                { 0.0D, 0.0D, 0.0d, (AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d,
                        AmmoType.LASER_BEAM.getInitHitRadius(), true },
                { 0.0D, 0.0D, 0.0d, (AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d,
                        -AmmoType.LASER_BEAM.getInitHitRadius(), true },

                /* hit, 90 degree */
                { 0.0D, 0.0D, 90.0d, 0.0d, -AmmoType.LASER_BEAM.getInitHitRadius(), true },
                { 0.0D, 0.0D, 90.0d, 0.0d, AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius(),
                        true },
                { 0.0D, 0.0D, 90.0d, AmmoType.LASER_BEAM.getInitHitRadius(),
                        (AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d, true },
                { 0.0D, 0.0D, 90.0d, -AmmoType.LASER_BEAM.getInitHitRadius(),
                        (AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d, true },

                /* hit, 180 degree */
                { 0.0D, 0.0D, 180.0d, AmmoType.LASER_BEAM.getInitHitRadius(), 0.0d, true },
                { 0.0D, 0.0D, 180.0d, -AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius(), 0.0d,
                        true },
                { 0.0D, 0.0D, 180.0d,
                        -(AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d,
                        -AmmoType.LASER_BEAM.getInitHitRadius(), true },
                { 0.0D, 0.0D, 180.0d,
                        -(AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d,
                        AmmoType.LASER_BEAM.getInitHitRadius(), true },

                /* hit, 270 degree */
                { 0.0D, 0.0D, 270.0d, 0.0d, AmmoType.LASER_BEAM.getInitHitRadius(), true },
                { 0.0D, 0.0D, 270.0d, 0.0d, -(AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()),
                        true },
                { 0.0D, 0.0D, 270.0d, AmmoType.LASER_BEAM.getInitHitRadius() - 0.001d,
                        -((AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d), true },
                { 0.0D, 0.0D, 270.0d, -AmmoType.LASER_BEAM.getInitHitRadius(),
                        -((AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d), true },

                /* no hit, 0 degree */
                { 0.0D, 0.0D, 0.0d, -1.0 - AmmoType.LASER_BEAM.getInitHitRadius(), 0.0d, false },
                { 0.0D, 0.0D, 0.0d, AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius() + 1.0d,
                        0.0d, false },
                { 0.0D, 0.0D, 0.0d, (AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d,
                        AmmoType.LASER_BEAM.getInitHitRadius() + 1.0d, false },
                { 0.0D, 0.0D, 0.0d, (AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d,
                        -AmmoType.LASER_BEAM.getInitHitRadius() - 1.0d, false },

                /* no hit, 90 degree */
                { 0.0D, 0.0D, 90.0d, 0.0d, -1.0 - AmmoType.LASER_BEAM.getInitHitRadius(), false },
                { 0.0D, 0.0D, 90.0d, 0.0d,
                        AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius() + 1.0d, false },
                { 0.0D, 0.0D, 90.0d, AmmoType.LASER_BEAM.getInitHitRadius() + 1.0d,
                        (AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d, false },
                { 0.0D, 0.0D, 90.0d, -AmmoType.LASER_BEAM.getInitHitRadius() - 1.0d,
                        (AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d, false },

                /* no hit, 180 degree */
                { 0.0D, 0.0D, 180.0d, 1.0 + AmmoType.LASER_BEAM.getInitHitRadius(), 0.0d, false },
                { 0.0D, 0.0D, 180.0d,
                        -1.0 * (AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius() + 1.0d), 0.0d,
                        false },
                { 0.0D, 0.0D, 180.0d,
                        -((AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d),
                        AmmoType.LASER_BEAM.getInitHitRadius() + 1.0d, false },
                { 0.0D, 0.0D, 180.0d,
                        -((AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d),
                        -AmmoType.LASER_BEAM.getInitHitRadius() - 1.0d, false },

                /* no hit, 270 degree */
                { 0.0D, 0.0D, 270.0d, 0.0d, -(-1.0 - AmmoType.LASER_BEAM.getInitHitRadius()), false },
                { 0.0D, 0.0D, 270.0d, 0.0d,
                        -(AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius() + 1.0d), false },
                { 0.0D, 0.0D, 270.0d, AmmoType.LASER_BEAM.getInitHitRadius() + 1.0d,
                        -((AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d), false },
                { 0.0D, 0.0D, 270.0d, -AmmoType.LASER_BEAM.getInitHitRadius() - 1.0d,
                        -((AmmoType.LASER_BEAM.getLength() + AmmoType.LASER_BEAM.getInitHitRadius()) / 2.0d),
                        false }, };
    }

    @Test(dataProvider = "hitCheckerInputList")
    public void testHitCheckerForLaserBeam(double beamStartX, double beamStartY, double angle, double objectX,
            double objectY, boolean expHitDetected) {
        Beam laserBeam = new Beam(new Point2D.Double(beamStartX, beamStartY), angle, 0, 0);

        Ship ship = ShipFactory.createShip(ShipConfig.INTERCEPTOR);
        ship.setCoordinate(objectX, objectY);
        boolean hitDetected = laserBeam.hits(ship);
        Assert.assertEquals(hitDetected, expHitDetected);
    }

    @Test
    public void testPhysicalRepresentation() {

        Beam laserBeam = new Beam(new Point2D.Double(0.0, 0.0), 0.0, 0, 0);

        Assert.assertEquals(laserBeam.getPhysicalRepresentation(),
                "{\"shape\" : \"line\", \"startx\": \"0.0\", \"starty\" :\"0.0\", \"endx\": \"1000.0\", \"endy\": \"0.0\"}");
    }

}
