package service;

import java.util.Random;

import org.springframework.stereotype.Component;

import config.GameConfig;
import interfaces.Spawnable;

@Component
public class Spawner {

	public static synchronized void spawn(Spawnable item){
		Random random = new Random();
		
		double px = random.nextInt((int)(GameConfig.STAGE_POS_LIMIT_X - GameConfig.STAGE_NEG_LIMIT_X));
		double py = random.nextInt((int)(GameConfig.STAGE_POS_LIMIT_Y - GameConfig.STAGE_NEG_LIMIT_Y));
		
		item.setX(px);
		item.setY(py);
	}
	
}
