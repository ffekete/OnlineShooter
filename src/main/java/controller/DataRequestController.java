package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import config.BrokerPaths;
import config.EndpointPaths;
import datahandler.PlayerPool;
import model.PlayerData;

/** Clients can poll REQUEST_PLAYER_DATA channel to receive server-side information about the given player. */
@Controller
public class DataRequestController {
	
	@Autowired
	private PlayerPool playerPool;
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA)
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA)
	public PlayerData getUserData(Long id){
		return playerPool.getPlayerById(id);
	}
}
