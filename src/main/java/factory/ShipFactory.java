package factory;

import java.util.List;

import game.datatypes.ship.CargoShip;
import game.datatypes.ship.Deltawing;
import game.datatypes.ship.Interceptor;
import game.datatypes.ship.Mercury;
import game.datatypes.ship.Quicksilver;
import game.interfaces.Ship;
import game.interfaces.SpawnableItem;

public class ShipFactory {
    public static Ship createShip(String shipType){
        switch (shipType){
            case "Quicksilver":
                return new Quicksilver();
            case "Interceptor":
                return new Interceptor();
            case "Mercury":
                return new Mercury();
            case "Deltawing":
                return new Deltawing();
            case "Cargoship":
            	List<SpawnableItem> carriage = CarriageBuilder.buildRandomCargo();
            	return new CargoShip(carriage);
            default:
                throw new RuntimeException("Unknownw ship type!");
        }
    }
}
