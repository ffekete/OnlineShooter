package scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import config.TimerValues;
import datahandler.PlayerPool;
import service.BulletDataProcessor;
import service.ItemProcessor;
import service.PlayerDataProcessor;

/** A basic scheduler for the main loop of the game. */
@Component
public class TaskScheduler {
	
	@Autowired
	ItemProcessor itemProcessor;
	
	@Autowired
	BulletDataProcessor bulletDataProcessor;
	
	@Autowired
	PlayerPool playerPool;
	
	@Autowired
	PlayerDataProcessor playerDataPrcessor;
	
	/** Main game loop. */
	@Scheduled(fixedRate = TimerValues.GAME_MAIN_PERIOD_IN_MS)
	public void run() throws InterruptedException{
		
		itemProcessor.updateItemData();
		
		bulletDataProcessor.updateBulletData();
		
		/* handle player inactivity counters */
		playerPool.updatePlayerPoolData();
		
		/* Do the math */
		playerDataPrcessor.updatePlayerData();
		
		
	}
}
