package game.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import game.datahandler.HighScoreTable;
import game.datatype.PlayerData;
import game.interfaces.Ammo;
import game.interfaces.AmmoPoolList;
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
    AmmoPoolList<Ammo> ammoPool;

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
        sentPlayerData.setVisibleAmmo(ammoPool.getAllOnScreen(playerId));
        sentPlayerData.setCoordinate(playerData.getCoordinate());
        sentPlayerData.setVisiblePlayers(playerPool.getAllOnScreen(playerId));
        sentPlayerData.setShipHp(playerData.getHp());
        sentPlayerData.setShipMaxHp(playerData.getSpaceShip().getMaxHp());
        sentPlayerData.setInvulnerable(playerData.isInvulnerable());
        sentPlayerData.setWeapon(playerData.getWeapon());
        sentPlayerData.setWeapons(playerData.getSpaceShip().getWeapons());
        sentPlayerData.setAmmoCount(playerData.getSpaceShip().getAmmoCount());
        sentPlayerData.setItems(itemPool.getAllOnScreen(playerId));
        sentPlayerData.setScore(playerData.getScore());
        sentPlayerData.setScores(highScoreTable.getThreeBestScores());
        sentPlayerData.setShieldAmount(playerData.getShield().getProtection());
        sentPlayerData.setMaxShieldAmount(playerData.getShield().getMaxProtectionValue());
        sentPlayerData.setRespawnTime(playerData.getRespawnTime());
        sentPlayerData.setColor(playerData.getColor());
        sentPlayerData.setShipType(playerData.getShipConfig().getType());
        sentPlayerData.setIsAI(playerData.getIsAI());
        sentPlayerData.setIsAsteroid(playerData.getIsAsteroid());
        sentPlayerData.setAllPlayersPosition(playerPool.getAllPlayersPosition());
        return sentPlayerData;
    }
}
