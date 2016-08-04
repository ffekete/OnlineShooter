package factory;

import interfaces.Ship;
import ship.Interceptor;
import ship.Mercury;
import ship.Quicksilver;

public class ShipFactory {
	public static Ship createShip(String shipType){
		switch (shipType){
			case "Quicksilver":
				return new Quicksilver();
			case "Interceptor":
				return new Interceptor();
			case "Mercury":
				return new Mercury();
			default:
				throw new RuntimeException("Unknownw ship type!");
		}
	}
}
