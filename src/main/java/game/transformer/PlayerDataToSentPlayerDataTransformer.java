package game.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import game.datahandler.BulletPool;
import game.datahandler.HighScoreTable;
import game.datahandler.ItemPool;
import game.datahandler.PlayerPool;
import game.datatypes.PlayerData;
import game.interfaces.Bullet;
import game.interfaces.BulletPoolList;
import game.model.SentPlayerData;

@Component
@Scope("prototype")
public class PlayerDataToSentPlayerDataTransformer {

    @Autowired
    PlayerPool playerPool;

    @Autowired
    BulletPoolList<Bullet> bulletPool;

    @Autowired
    ItemPool itemPool;

    @Autowired
    HighScoreTable highScoreTable;

    public SentPlayerData transform(PlayerData playerData) {

        SentPlayerData sentPlayerData = new SentPlayerData();
        Long playerId = playerData.getId();

        sentPlayerData.setConnectionId(playerData.getConnectionId());
        sentPlayerData.setId(playerId);
        sentPlayerData.setShipAngle(playerData.getShipAngle());
        sentPlayerData.setVisibleBullets(bulletPool.getAllOnScreen(playerId));
        sentPlayerData.setX(playerData.getX());
        sentPlayerData.setY(playerData.getY());
        sentPlayerData.setVisiblePlayers(playerPool.getAllPlayersOnScreen(playerId));
        sentPlayerData.setShipHp(playerData.getHp());
        sentPlayerData.setInvulnerable(playerData.isInvulnerable());
        sentPlayerData.setWeapon(playerData.getWeapon());
        sentPlayerData.setItems(itemPool.getAllOnScreen(playerId));
        sentPlayerData.setScore(playerData.getScore());
        sentPlayerData.setScores(highScoreTable.getThreeBestScores());
        sentPlayerData.setShieldAmount(playerData.getShield().getProtection());
        sentPlayerData.setMaxShieldAmount(playerData.getShield().getMaxProtectionValue());
        sentPlayerData.setRespawnTime(playerData.getRespawnTime());
        sentPlayerData.setColor(playerData.getColor());
        sentPlayerData.setShipType(playerData.getShipType());
        return sentPlayerData;
    }
}
