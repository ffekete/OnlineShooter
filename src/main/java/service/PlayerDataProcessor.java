package service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import datahandler.PlayerPool;
import model.PlayerData;

@Component
public class PlayerDataProcessor {
	@Autowired
	PlayerPool playerPool;
	
	private double calculateAngle(Long targetX, Long targetY) {
	    double angle = Math.toDegrees(Math.atan2(targetY - 0, targetX - 0));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	public void updateShipAngles() throws InterruptedException{
		if(playerPool != null){
			Iterator<Long> shipIds = playerPool.getPool().keySet().iterator();
			
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
