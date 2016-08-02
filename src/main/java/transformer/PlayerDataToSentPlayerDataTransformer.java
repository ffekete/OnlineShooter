package transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import datahandler.BulletPool;
import datahandler.HighScoreTable;
import datahandler.ItemPool;
import datahandler.PlayerPool;
import model.PlayerData;
import model.SentPlayerData;

@Component
@Scope("prototype")
public class PlayerDataToSentPlayerDataTransformer {
	
	@Autowired
	PlayerPool playerPool;
	
	@Autowired
	BulletPool bulletPool;
	
	@Autowired
	ItemPool itemPool;
	
	@Autowired
	HighScoreTable highScoreTable;
	
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
			sentPlayerData.setInvulnerable(playerData.isInvulnerable());
			sentPlayerData.setWeapon(playerData.getWeapon());
			sentPlayerData.setItems(itemPool.getAllItemsOnScreen(playerData));
			sentPlayerData.setScore(playerData.getScore());
			sentPlayerData.setScores(highScoreTable.getThreeBestScores());
			sentPlayerData.setShieldAmount(playerData.getShield().getProtection());
			sentPlayerData.setMaxShieldAmount(playerData.getShield().getMaxProtectionValue());
			sentPlayerData.setRespawnTime(playerData.getRespawnTime());
			sentPlayerData.setColor(playerData.getColor());
			return sentPlayerData;
		}
		else
		{
			return null;
		}
	}
}
