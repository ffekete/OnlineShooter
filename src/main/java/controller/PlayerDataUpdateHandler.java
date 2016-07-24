package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import config.EndpointPaths;
import datahandler.PlayerPool;
import model.PlayerData;
import model.ReceivedPlayerData;
import transformer.ReceivedPlayerDataToPlayerDataTransformer;

@Controller
public class PlayerDataUpdateHandler {

	@Autowired
	PlayerPool playerPool;
	
	@Autowired
	ReceivedPlayerDataToPlayerDataTransformer receivedPlayerDataToPlayerDataTransformer;
	
	@MessageMapping(EndpointPaths.UPDATE_PLAYER_DATA)
	public void updateplayerData(ReceivedPlayerData receivedPlayerData){
		PlayerData playerData = receivedPlayerDataToPlayerDataTransformer.transform(receivedPlayerData);
		if(playerData != null){
			//System.out.println("Player data updated: " + playerData.getId() + " " + playerPool.getPlayerById(playerData.getId()));
		}
		else
		{
			System.out.println("Failed to update player data: " + receivedPlayerData.getId());
		}
	}
}
