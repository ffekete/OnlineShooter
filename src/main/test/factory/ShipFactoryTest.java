package factory;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.ShipConfig;
import game.datatype.ship.Asteroid;
import game.datatype.ship.CargoShip;
import game.datatype.ship.Deltawing;
import game.datatype.ship.Interceptor;
import game.datatype.ship.Mercury;
import game.datatype.ship.Quicksilver;
import game.interfaces.Ship;

public class ShipFactoryTest {
    @Test
    public void testShouldCreateCargoShip() {
        Ship ship = ShipFactory.createShip(ShipConfig.CARGOSHIP);
        Assert.assertTrue(ship instanceof CargoShip);
    }

    @Test
    public void testShouldCreateDeltaWing() {
        Ship ship = ShipFactory.createShip(ShipConfig.DELTAWING);
        Assert.assertTrue(ship instanceof Deltawing);
    }

    @Test
    public void testShouldCreateInterceptor() {
        Ship ship = ShipFactory.createShip(ShipConfig.INTERCEPTOR);
        Assert.assertTrue(ship instanceof Interceptor);
    }

    @Test
    public void testShouldCreateMercury() {
        Ship ship = ShipFactory.createShip(ShipConfig.MERCURY);
        Assert.assertTrue(ship instanceof Mercury);
    }

    @Test
    public void testShouldCreateQuicksilver() {
        Ship ship = ShipFactory.createShip(ShipConfig.QUICKSILVER);
        Assert.assertTrue(ship instanceof Quicksilver);
    }

    @Test
    public void testShouldCreateAsteroid() {
        Ship ship = ShipFactory.createShip(ShipConfig.ASTEROID);
        Assert.assertTrue(ship instanceof Asteroid);
    }
}
