package factory;

import game.config.constant.ShipConfig;
import game.datatype.ship.Asteroid;
import game.datatype.ship.CargoShip;
import game.datatype.ship.Deltawing;
import game.datatype.ship.Interceptor;
import game.datatype.ship.Mercury;
import game.datatype.ship.Quicksilver;
import game.interfaces.Ship;

public class ShipFactory {
    public static Ship createShip(ShipConfig config) {
        switch (config) {
        case QUICKSILVER:
            return new Quicksilver();
        case INTERCEPTOR:
            return new Interceptor();
        case MERCURY:
            return new Mercury();
        case DELTAWING:
            return new Deltawing();
        case CARGOSHIP:
            return new CargoShip(CarriageBuilder.buildHalfCargo(ShipConfig.CARGOSHIP.getCargoCapacity()));
        case ASTEROID:
            return new Asteroid();
        default:
            throw new RuntimeException("Unknownw ship type!");
        }
    }
}