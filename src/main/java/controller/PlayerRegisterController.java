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
import model.QualifiedPlayerData;
import transformer.PlayerDataToQualifiedPlayerDataTransformer;

/** Serves all incoming register new player requests. */
@Controller
public class PlayerRegisterController {

	@Autowired
	private PlayerPool playerPool;
	
	@Autowired
	PlayerDataToQualifiedPlayerDataTransformer playerDataToQualifiedPlayerDataTransformer;
	
	/** Receives new player registration request on endpoint REGISTER_PLAYER and sends answers by broker REGISTER_PLAYERED_STATUS. */
	@MessageMapping(EndpointPaths.REGISTER_PLAYER)
	@SendTo(BrokerPaths.PLAYER_REGISTERED_STATUS)
	public QualifiedPlayerData registerNewPlayer(String name){
		Long newId = PlayerIdGenerator.generateNewId();
		QualifiedPlayerData qualifiedPlayerData = new QualifiedPlayerData();
		
		System.out.println("New player registration request received with name " + name + ".");
		
		if(playerPool.registerPlayer(newId, name)){
			PlayerData playerData = playerPool.getPlayerById(newId);
			qualifiedPlayerData = playerDataToQualifiedPlayerDataTransformer.tranform(playerData, true);
		}
		
		return qualifiedPlayerData;
	}
}
