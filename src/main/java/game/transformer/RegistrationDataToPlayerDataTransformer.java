package game.transformer;

import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;
import game.datatype.RegistrationData;

public class RegistrationDataToPlayerDataTransformer {

    public PlayerData transform(RegistrationData data, Long newPlayerId) {
        AIDao aiDao = new AIDao(data.getIsAI(), data.getIsAsteroid());
        return new PlayerData(newPlayerId, data.getName(), ShipConfig.getSpecificConfig(data.getShipType()), aiDao);
    }
}
