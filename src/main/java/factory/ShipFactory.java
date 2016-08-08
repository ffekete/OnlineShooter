package factory;

import game.datatypes.ship.Deltawing;
import game.datatypes.ship.Interceptor;
import game.datatypes.ship.Mercury;
import game.datatypes.ship.Quicksilver;
import game.interfaces.Ship;

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
			default:
				throw new RuntimeException("Unknownw ship type!");
		}
	}
}
