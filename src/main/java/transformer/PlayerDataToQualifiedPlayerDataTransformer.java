package transformer;

import org.springframework.stereotype.Component;

import model.PlayerData;
import model.QualifiedPlayerData;

@Component
public class PlayerDataToQualifiedPlayerDataTransformer {

	public QualifiedPlayerData tranform(PlayerData playerData, boolean registrartionStatus){
		QualifiedPlayerData qualifiedPlayerData = new QualifiedPlayerData();
		qualifiedPlayerData.setPlayerData(playerData);
		qualifiedPlayerData.setRegistered(registrartionStatus);
		
		return qualifiedPlayerData;
	}
	
}
