package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import config.EndpointPaths;
import datahandler.PlayerPool;
import model.PlayerData;
import model.ReceivedPlayerData;
import transformer.ReceivedPlayerDataToPlayerDataTransformer;

/** this class serves all data update request coming from client side. */
@Controller
public class PlayerDataUpdateHandler {

	@Autowired
	PlayerPool playerPool;
	
	@Autowired
	ReceivedPlayerDataToPlayerDataTransformer receivedPlayerDataToPlayerDataTransformer;
	
	/** Function that handles update player data requests when a new message arrives to destination endpoint UPDATE_PLAYER_DATA. */
	@MessageMapping(EndpointPaths.UPDATE_PLAYER_DATA)
	public void updateplayerData(ReceivedPlayerData receivedPlayerData){
		if(receivedPlayerData != null){
			PlayerData playerData = receivedPlayerDataToPlayerDataTransformer.transform(receivedPlayerData);
			if(playerData == null){
				System.out.println("Failed to update player data: " + receivedPlayerData.getId());
			}
		}
		else
		{
			System.out.println("!!!Error: Player data received with null reference. ");
		}
	}
}
