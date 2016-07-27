package transformer;

import org.springframework.stereotype.Component;

import model.PlayerData;
import model.QualifiedPlayerData;

@Component
public class PlayerDataToQualifiedPlayerDataTransformer {

	public QualifiedPlayerData tranform(PlayerData playerData, boolean registrartionStatus){
		if(playerData != null){
			QualifiedPlayerData qualifiedPlayerData = new QualifiedPlayerData();
			qualifiedPlayerData.setPlayerData(playerData);
			qualifiedPlayerData.setRegistered(registrartionStatus);
		
			return qualifiedPlayerData;
		}
		return null;
	}
	
}
