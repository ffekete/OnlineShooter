package bullets;

import java.awt.geom.Point2D;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.datatypes.bullet.BulletData;
import game.interfaces.Bullet;

public class BulletDataTest {

    @Test
    public void testPhysicalRepresentation() {
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        Bullet bullet = new BulletData(coordinate, 90.0, 0L, 5);

        Assert.assertEquals(bullet.getPhysicalRepresentation(),
                "{\"shape\": \"circle\", \"startx\": \"0.0\", \"starty\": \"0.0\", \"radius\" : \"15\"}");
    }
}
