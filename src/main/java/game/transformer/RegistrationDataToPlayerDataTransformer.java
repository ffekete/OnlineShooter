package game.transformer;

import org.springframework.stereotype.Component;

import game.datatype.PlayerData;
import game.datatype.RegistrationData;

@Component
public class RegistrationDataToPlayerDataTransformer {

    public PlayerData transform(RegistrationData data, Long newPlayerId) {
        return new PlayerData(newPlayerId, data.getName(), data.getShipType(), data.getIsAI());
    }
}
