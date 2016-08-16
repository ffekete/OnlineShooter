package game.transformer;

import org.springframework.stereotype.Component;

import game.datatypes.PlayerData;
import game.datatypes.RegistrationData;

@Component
public class RegistrationDataToPlayerDataTransformer {

	public PlayerData transform(RegistrationData data, Long newPlayerId){
		PlayerData newPlayerData = new PlayerData(newPlayerId, data.getName(), data.getShipType());
		
		return newPlayerData;
	}
}
