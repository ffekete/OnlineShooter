package factory;

import java.util.List;

import game.config.constants.ShipConfig;
import game.datatypes.ship.CargoShip;
import game.datatypes.ship.Deltawing;
import game.datatypes.ship.Interceptor;
import game.datatypes.ship.Mercury;
import game.datatypes.ship.Quicksilver;
import game.interfaces.Ship;
import game.interfaces.SpawnableItem;

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
            List<SpawnableItem> carriage = CarriageBuilder.buildRandomCargo();
            return new CargoShip(carriage);
        default:
            throw new RuntimeException("Unknownw ship type!");
        }
    }
}
