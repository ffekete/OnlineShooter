package factory;

import java.awt.geom.Point2D;

import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Test;

import game.interfaces.Bullet;

public class BulletBuilderTest {

    @Mock
    BulletBuilder bulletBuilder;

    @Mock
    Point2D coordinates;

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ".*coordinate.*")
    public void testShouldThrowCoordinateException() {
        BulletBuilder bulletBuilder = new BulletBuilder();
        bulletBuilder.setCoordinate(null).build();
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ".*angle.*")
    public void testShouldThrowAngleException() {
        BulletBuilder bulletBuilder = new BulletBuilder();
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        bulletBuilder.setCoordinate(coordinate).build();
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ".*playerId.*")
    public void testShouldThrowPlayerIdException() {
        BulletBuilder bulletBuilder = new BulletBuilder();
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        bulletBuilder.setCoordinate(coordinate).setAngle(1.0).build();
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ".*damage.*")
    public void testShouldThrowDamageException() {
        BulletBuilder bulletBuilder = new BulletBuilder();
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        bulletBuilder.setCoordinate(coordinate).setAngle(1.0).setPlayerId(1).build();
    }

    @Test
    public void testShouldBuildBullet() {
        BulletBuilder bulletBuilder = new BulletBuilder();
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        Bullet bullet = bulletBuilder.setCoordinate(coordinate).setAngle(1.0).setPlayerId(1).setDamage(1).build();

        Assert.assertTrue(bullet instanceof Bullet);
    }
}
