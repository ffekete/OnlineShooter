package game.datatype.ammo;

import java.awt.geom.Point2D;

import org.testng.Assert;
import org.testng.annotations.Test;

import factory.AmmoBuilder;
import game.config.constant.AmmoType;
import game.interfaces.Ammo;

public class BulletTest {
	
    @Test
    public void testPhysicalRepresentation() {
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        Ammo bullet = new AmmoBuilder()
                            .setCoordinate(coordinate)
                            .setAngle(90.0)
                            .setDamageBonus(0)
                            .setPlayerId(0L)
                            .build(AmmoType.BULLET);

        Assert.assertEquals(bullet.getPhysicalRepresentation(),
                "{\"shape\": \"circle\", \"startx\": \"0.0\", \"starty\": \"0.0\", \"radius\" : \"2\"}");
    }
}
