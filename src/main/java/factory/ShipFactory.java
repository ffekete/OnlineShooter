package factory;

import config.ShipId;
import interfaces.Ship;
import ship.Interceptor;
import ship.Mercury;
import ship.Quicksilver;

public class ShipFactory {
	public static Ship createShip(ShipId shipId){
		switch (shipId){
			case SHIP_QUICKSILVER:
				return new Quicksilver();
			case SHIP_INTERCEPTOR:
				return new Interceptor();
			case SHIP_MERCURY:
				return new Mercury();
			default:
				return null;
		}
	}
}
