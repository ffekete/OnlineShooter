package service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.CanvasConstants;
import config.GameConfig;
import datahandler.PlayerPool;
import model.PlayerData;

/** Basic class to calculate player related values e.g. ship angles,... */
@Component
public class PlayerDataProcessor {
	@Autowired
	PlayerPool playerPool;
	
	/** Calculates an angle using two points. */
	private double calculateAngle(Long targetX, Long targetY, Long baseX, Long baseY) {
	    double angle = Math.toDegrees(Math.atan2(targetY - baseY, targetX - baseX));

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
				nextShip.setAngle(calculateAngle(nextShip.getMouseX(), nextShip.getMouseY(), CanvasConstants.CANVAS_HALF_WIDTH, CanvasConstants.CANVAS_HALF_HEIGHT));	
			}
		}
	}
	
	public void updatePlayerCoordinates(){
		if(playerPool != null){
			Iterator<Long> shipIds = playerPool.getPool().keySet().iterator();
			
			while(shipIds.hasNext()){
				Long nextShipId = shipIds.next();
				PlayerData nextShip = playerPool.getPlayerById(nextShipId);
				
				double resultx;
				double resulty;
				double angle = nextShip.getAngle() * Math.PI / 180;
				
				resultx = nextShip.getX() + GameConfig.SHIP_INITIAL_SPEED * Math.cos(angle);
				resulty = nextShip.getY() + GameConfig.SHIP_INITIAL_SPEED * Math.sin(angle);
				
				nextShip.setX((long)resultx);
				nextShip.setY((long)resulty);
			}
		}
	}
}
