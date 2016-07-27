package transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import datahandler.BulletsPool;
import datahandler.PlayerPool;
import model.PlayerData;
import model.SentPlayerData;

@Component
@Scope("prototype")
public class PlayerDataToSentPlayerDataTransformer {
	
	@Autowired
	PlayerPool playerPool;
	
	@Autowired
	BulletsPool bulletPool;
	
	public SentPlayerData transform(PlayerData playerData){
		if(playerData != null){
			SentPlayerData sentPlayerData = new SentPlayerData();
			Long playerId = playerData.getId();
			
			sentPlayerData.setConnectionId(playerData.getConnectionId());
			sentPlayerData.setId(playerId);
			sentPlayerData.setShipAngle(playerData.getShipAngle());
			sentPlayerData.setVisibleBullets(bulletPool.getAllBulletsOnScreen(playerId));
			sentPlayerData.setX(playerData.getX());
			sentPlayerData.setY(playerData.getY());
			sentPlayerData.setVisiblePlayers(playerPool.getAllPlayersOnScreen(playerId));
			sentPlayerData.setShipHp(playerData.getHp());
			sentPlayerData.setInvulnerable(playerData.getInvulnerabilityCounter() > 0L);
			return sentPlayerData;
		}
		else
		{
			return null;
		}
	}
}
