package scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import config.TimerValues;
import connection.ConnectionPool;
import datahandler.BulletsPool;
import datahandler.PlayerPool;
import service.BulletDataProcessor;
import service.PlayerDataProcessor;

/** A basic scheduler for the main loop of the game. */
@Component
public class TaskScheduler {
	
	@Autowired
	BulletDataProcessor bulletDataProcessor;
	
	@Autowired
	PlayerPool playerPool;
	
	@Autowired
	PlayerDataProcessor playerDataPrcessor;
	
	/** Main game loop. */
	@Scheduled(fixedRate = TimerValues.GAME_MAIN_PERIOD_IN_MS)
	public void run() throws InterruptedException{
		
		bulletDataProcessor.updateBulletCoordinates();
		
		/* handle player inactivity counters */
		playerPool.increasePlayerInactivityCounters();
		playerPool.removeInactivePlayers();
		
		/* Do the math */
		playerDataPrcessor.updateShipAngles();
		
	}
}
