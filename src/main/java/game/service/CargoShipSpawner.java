package game.service;

import java.util.Random;

import game.config.GameConfig;
import game.datatypes.Coordinate;
import game.interfaces.Spawnable;

public class CargoShipSpawner extends Spawner{

	private static double pickRandomAngle(){
		Random random = new Random();
		
		return (double)random.nextInt(360);
	}
	
	private static Coordinate pickRandomCoordinateForItemOnGameSpace(){
		Random random = new Random();
		
		double px = GameConfig.STAGE_POS_LIMIT_X - random.nextInt((int)(GameConfig.STAGE_POS_LIMIT_X - GameConfig.STAGE_NEG_LIMIT_X));
        double py = GameConfig.STAGE_POS_LIMIT_Y - random.nextInt((int)(GameConfig.STAGE_POS_LIMIT_Y - GameConfig.STAGE_NEG_LIMIT_Y));
        
        return new Coordinate(px, py);
	}
	
    public static void spawn(Spawnable item){
        Coordinate coordinate = pickRandomCoordinateForItemOnGameSpace();
        
        item.setX(coordinate.getX());
        item.setY(coordinate.getY());
        item.setAngle(pickRandomAngle());
    }
}
