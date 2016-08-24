package factory;

import game.config.constant.ShipConfig;
import game.datatype.ship.CargoShip;
import game.datatype.ship.Deltawing;
import game.datatype.ship.Interceptor;
import game.datatype.ship.Mercury;
import game.datatype.ship.Quicksilver;
import game.interfaces.Ship;

public class ShipFactory {
    public static Ship createShip(String shipType) {
        switch (shipType) {
        case ShipConfig.SHIP_TYPE_QUICKSILVER:
            return new Quicksilver();
        case ShipConfig.SHIP_TYPE_INTERCEPTOR:
            return new Interceptor();
        case ShipConfig.SHIP_TYPE_MERCURY:
            return new Mercury();
        case ShipConfig.SHIP_TYPE_DELTAWING:
            return new Deltawing();
        case ShipConfig.SHIP_TYPE_CARGOSHIP:
            return new CargoShip(CarriageBuilder.buildHalfCargo(ShipConfig.CARGO_SHIP_CARGO_CAPACITY));
        default:
            throw new RuntimeException("Unknownw ship type!");
        }
    }
}