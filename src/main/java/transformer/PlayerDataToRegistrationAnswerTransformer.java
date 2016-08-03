package transformer;

import org.springframework.stereotype.Component;

import model.PlayerData;
import model.RegistrationAnswer;

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
