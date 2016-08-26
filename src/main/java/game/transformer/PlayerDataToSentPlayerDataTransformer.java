package game.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import game.datahandler.HighScoreTable;
import game.datatype.PlayerData;
import game.interfaces.Bullet;
import game.interfaces.BulletPoolList;
import game.interfaces.ItemPoolList;
import game.interfaces.PlayerPoolMap;
import game.interfaces.SpawnableItem;
import game.model.SentPlayerData;

@Component
@Scope("prototype")
public class PlayerDataToSentPlayerDataTransformer {

    @Autowired
    PlayerPoolMap<Long, PlayerData> playerPool;

    @Autowired
    BulletPoolList<Bullet> bulletPool;

    @Autowired
    ItemPoolList<SpawnableItem> itemPool;

    @Autowired
    HighScoreTable highScoreTable;

    public SentPlayerData transform(PlayerData playerData) {

        SentPlayerData sentPlayerData = new SentPlayerData();
        Long playerId = playerData.getId();

        sentPlayerData.setConnectionId(playerData.getConnectionId());
        sentPlayerData.setId(playerId);
        sentPlayerData.setShipAngle(playerData.getShipAngle());
        sentPlayerData.setVisibleBullets(bulletPool.getAllOnScreen(playerId));
        sentPlayerData.setCoordinate(playerData.getCoordinate());
        sentPlayerData.setVisiblePlayers(playerPool.getAllOnScreen(playerId));
        sentPlayerData.setShipHp(playerData.getHp());
        sentPlayerData.setShipMaxHp(playerData.getSpaceShip().getMaxHp());
        sentPlayerData.setInvulnerable(playerData.isInvulnerable());
        sentPlayerData.setWeapon(playerData.getWeapon());
        sentPlayerData.setWeapons(playerData.getSpaceShip().getWeapons());
        sentPlayerData.setItems(itemPool.getAllOnScreen(playerId));
        sentPlayerData.setScore(playerData.getScore());
        sentPlayerData.setScores(highScoreTable.getThreeBestScores());
        sentPlayerData.setShieldAmount(playerData.getShield().getProtection());
        sentPlayerData.setMaxShieldAmount(playerData.getShield().getMaxProtectionValue());
        sentPlayerData.setRespawnTime(playerData.getRespawnTime());
        sentPlayerData.setColor(playerData.getColor());
        sentPlayerData.setShipType(playerData.getShipType());
        sentPlayerData.setIsAI(playerData.getIsAI());
        sentPlayerData.setIsAsteroid(playerData.getIsAsteroid());
        return sentPlayerData;
    }
}
