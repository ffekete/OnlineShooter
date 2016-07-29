package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import config.EndpointPaths;
import datahandler.BulletPool;

@Controller
public class NewBulletHandler {

	@Autowired
	BulletPool bulletPool;
	
	@MessageMapping(EndpointPaths.CREATE_BULLET)
	public void handleNewBulletRequest(Long playerId){
		bulletPool.addBullet(playerId);
	}
}
