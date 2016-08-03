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
import model.RegistrationAnswer;
import model.RegistrationData;
import transformer.PlayerDataToRegistrationAnswerTransformer;

/** Serves all incoming register new player requests. */
@Controller
public class PlayerRegisterController {

	@Autowired
	MessageSender messageSender;
	
	@Autowired
	private PlayerPool playerPool;
	
	@Autowired
	PlayerDataToRegistrationAnswerTransformer playerDataToQualifiedPlayerDataTransformer;
	
	/** Receives new player registration request on endpoint REGISTER_PLAYER and sends answers by broker REGISTER_PLAYERED_STATUS. */
	@MessageMapping(EndpointPaths.REGISTER_PLAYER)
	@SendTo(BrokerPaths.PLAYER_REGISTERED_STATUS)
	public RegistrationAnswer registerNewPlayer(RegistrationData data){
		RegistrationAnswer registrationAnswer = new RegistrationAnswer();
		
		if(data.getName() != null){
			Long newId = PlayerIdGenerator.generateNewId();
					
			System.out.println("New player registration request received with name " + data.getName() +" and ship " + data.getShipType() + ".");
			
			if(playerPool.registerPlayer(newId, data)){
				PlayerData playerData = playerPool.getPlayerById(newId);
				registrationAnswer = playerDataToQualifiedPlayerDataTransformer.tranform(playerData, true);
				System.out.println("Player registered with id " + newId + " and name " + data.getName() + ". Connection id is " + playerData.getConnectionId());
				messageSender.broadCastMessage("Player " + data.getName() + " entered the game." );
			}
		}
		
		return registrationAnswer;
	}
}
