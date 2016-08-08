package game.transformer;

import org.springframework.stereotype.Component;

import game.model.PlayerData;
import game.model.RegistrationAnswer;

@Component
public class PlayerDataToRegistrationAnswerTransformer {

	public RegistrationAnswer tranform(PlayerData playerData, boolean registrartionStatus){
		if(playerData != null){
			RegistrationAnswer registrationAnswer = new RegistrationAnswer();
			registrationAnswer.setPlayerData(playerData);
			registrationAnswer.setRegistered(registrartionStatus);
		
			return registrationAnswer;
		}
		return null;
	}
	
}
