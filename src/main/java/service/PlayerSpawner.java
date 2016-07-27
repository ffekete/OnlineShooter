package service;

import java.util.Random;

import org.springframework.stereotype.Controller;

import config.GameConfig;
import model.PlayerData;

@Controller
public class PlayerSpawner {

	public void spawn(PlayerData player){
		Random random = new Random();
		
		long px = random.nextInt((int)(GameConfig.STAGE_POS_LIMIT_X - GameConfig.STAGE_NEG_LIMIT_X));
		long py = random.nextInt((int)(GameConfig.STAGE_POS_LIMIT_Y - GameConfig.STAGE_NEG_LIMIT_Y));
		
		player.setX(px);
		player.setX(py);
	}
	
}
