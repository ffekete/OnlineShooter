package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import config.BrokerPaths;
import config.EndpointPaths;
import datahandler.PlayerIdGenerator;
import datahandler.PlayerPool;
import model.PlayerData;

@Controller
public class PlayerRegisterController {

	@Autowired
	private PlayerPool playerPool;
	
	@MessageMapping(EndpointPaths.REGISTER_PLAYER)
	@SendTo(BrokerPaths.PLAYER_REGISTERED_STATUS)
	public PlayerData registerNewPlayer(String name){
		Long newId = PlayerIdGenerator.generateNewId();
		
		System.out.println("New player registration request received with name " + name + ".");
		
		if(playerPool.registerPlayer(newId, name)){
			return playerPool.getPlayerById(newId);
		}
		return null;
	}
}
