package service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import config.CanvasConstants;
import datahandler.PlayerPool;
import model.PlayerData;

@Component
public class PlayerDataProcessor {
	@Autowired
	PlayerPool playerPool;
	
	private double calculateAngle(Long targetX, Long targetY) {
	    double angle = Math.toDegrees(Math.atan2(targetY - CanvasConstants.CANVAS_HALF_HEIGHT, targetX - CanvasConstants.CANVAS_HALF_WIDTH));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	public void updateShipAngles() throws InterruptedException{
		
		if(playerPool != null){
			Iterator<Long> shipIds = playerPool.getPool().keySet().iterator();
			
			System.out.println("****************************************");
			while(shipIds.hasNext()){
				Long nextShipId = shipIds.next();
				PlayerData nextShip = playerPool.getPlayerById(nextShipId);
				nextShip.setAngle(calculateAngle(nextShip.getMouseX(), nextShip.getMouseY()));	
				System.out.println(nextShip.getAngle());
			}
		}
		else
		{
			System.out.println("player pool not initialized!");
			Thread.sleep(100);
		}
	}
}
