package game.datahandler;

import org.springframework.beans.factory.annotation.Autowired;

import game.config.constant.GameConfig;
import game.config.constant.ShipConfig;
import game.datatype.PlayerData;
import game.datatype.RegistrationData;
import game.interfaces.AIBase;
import game.interfaces.PlayerPoolMap;

public class AIHandler implements AIBase {

    @Autowired
    private PlayerPoolMap<Long, PlayerData> playerPool;

    @Override
    public void updateAIData() {
        if (!playerPool.hasAIOnScreen()) {
            RegistrationData data = new RegistrationData();
            data.setName(GameConfig.AI);
            data.setShipType(ShipConfig.SHIP_TYPE_CARGOSHIP);
            data.setColor("#FF0000");
            data.setIsAI(true);
            playerPool.registerPlayer(PlayerIdGenerator.generateNewId(), data);
        }
    }
}