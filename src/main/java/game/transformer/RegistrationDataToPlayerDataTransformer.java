package game.transformer;

import game.datatype.PlayerData;
import game.datatype.RegistrationData;

public class RegistrationDataToPlayerDataTransformer {

    public PlayerData transform(RegistrationData data, Long newPlayerId) {
        return new PlayerData(newPlayerId, data.getName(), data.getShipType(), data.getIsAI());
    }
}
