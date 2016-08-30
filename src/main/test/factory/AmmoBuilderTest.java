package factory;

import java.awt.geom.Point2D;

import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.AmmoType;
import game.datatype.PlayerData;
import game.interfaces.Ammo;

public class AmmoBuilderTest {

    @Mock
    AmmoBuilder ammoBuilder;

    @Mock
    Point2D coordinates;

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ".*coordinate.*")
    public void testShouldThrowCoordinateException() {
        AmmoBuilder ammoBuilder = new AmmoBuilder();
        ammoBuilder.setCoordinate(null).build(AmmoType.BULLET);
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ".*angle.*")
    public void testShouldThrowAngleException() {
        AmmoBuilder ammoBuilder = new AmmoBuilder();
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        ammoBuilder.setCoordinate(coordinate).build(AmmoType.BULLET);
    }
    
    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ".*damage bonus.*")
    public void testShouldThrowDamageBonusException() {
        AmmoBuilder ammoBuilder = new AmmoBuilder();
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        ammoBuilder.setCoordinate(coordinate).setAngle(1.0).build(AmmoType.BULLET);
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ".*playerId.*")
    public void testShouldThrowPlayerIdException() {
        AmmoBuilder ammoBuilder = new AmmoBuilder();
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        ammoBuilder.setCoordinate(coordinate).setAngle(1.0).setDamageBonus(0).build(AmmoType.BULLET);
    }

    @Test
    public void testShouldBuildBullet() {
        AmmoBuilder ammoBuilder = new AmmoBuilder();
        Point2D coordinate = new Point2D.Double(0.0, 0.0);
        Ammo ammo = ammoBuilder.setCoordinate(coordinate).setAngle(1.0).setDamageBonus(0).setPlayerId(1).build(AmmoType.BULLET);

        Assert.assertTrue(ammo instanceof Ammo);
    }
}
