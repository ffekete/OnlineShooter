package transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import datahandler.PlayerPool;
import model.PlayerData;
import model.ReceivedPlayerData;

@Component
public class ReceivedPlayerDataToPlayerDataTransformer {
	@Autowired
	PlayerPool playerPool;
	
	public PlayerData transform(ReceivedPlayerData receivedPlayerData){
		PlayerData playerData = playerPool.getPlayerById(receivedPlayerData.getId());
		
		if(playerData != null){
			playerData.setMouseX(receivedPlayerData.getMouseX());
			playerData.setMouseY(receivedPlayerData.getMouseY());
			return playerData;
		}
		else
		{
			return null;
		}
	}
}
