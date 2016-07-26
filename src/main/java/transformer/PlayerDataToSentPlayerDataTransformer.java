package transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import datahandler.BulletsPool;
import model.PlayerData;
import model.SentPlayerData;

@Component
@Scope("prototype")
public class PlayerDataToSentPlayerDataTransformer {
	
	@Autowired
	BulletsPool bulletPool;
	
	public SentPlayerData transform(PlayerData playerData){
		SentPlayerData sentPlayerData = new SentPlayerData();
		
		sentPlayerData.setConnectionId(playerData.getConnectionId());
		sentPlayerData.setId(playerData.getId());
		sentPlayerData.setShipAngle(playerData.getShipAngle());
		sentPlayerData.setVisibleBullets(bulletPool.getAllBulletsOnScreen(playerData.getId()));
		sentPlayerData.setX(playerData.getX());
		sentPlayerData.setY(playerData.getY());
		
		return sentPlayerData;
	}
}
