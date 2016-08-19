package bullets;

import java.awt.geom.Point2D;

import org.testng.Assert;
import org.testng.annotations.Test;

import factory.BulletBuilder;
import game.datatypes.bullet.BulletData;
import game.interfaces.Bullet;

public class BulletDataTest {

    @Test
    public void testPhysicalRepresentation() {
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        Bullet bullet = new BulletBuilder()
                            .setCoordinate(coordinate)
                            .setAngle(90.0)
                            .setPlayerId(0L)
                            .setDamage(5)
                            .build();

        Assert.assertEquals(bullet.getPhysicalRepresentation(),
                "{\"shape\": \"circle\", \"startx\": \"0.0\", \"starty\": \"0.0\", \"radius\" : \"15\"}");
    }
}
