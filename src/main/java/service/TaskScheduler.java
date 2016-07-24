package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduler {
	
	@Autowired
	PlayerDataProcessor playerDataPrcessor;
	
	@Scheduled(fixedRate = 100)
	public void run() throws InterruptedException{
		playerDataPrcessor.updateShipAngles();
	}
}
