package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import config.TimerValues;

@Component
public class TaskScheduler {
	
	@Autowired
	PlayerDataProcessor playerDataPrcessor;
	
	@Scheduled(fixedRate = TimerValues.GAME_MAIN_PERIOD_IN_MS)
	public void run() throws InterruptedException{
		playerDataPrcessor.updateShipAngles();
	}
}
