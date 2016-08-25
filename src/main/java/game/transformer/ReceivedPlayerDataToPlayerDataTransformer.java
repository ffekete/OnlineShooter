package game.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.datatype.PlayerData;
import game.datatype.ReceivedPlayerData;
import game.interfaces.PlayerPoolMap;

@Component
public class ReceivedPlayerDataToPlayerDataTransformer {
    @Autowired
    PlayerPoolMap<Long, PlayerData> playerPool;

    public PlayerData transform(ReceivedPlayerData receivedPlayerData) {
        PlayerData playerData = playerPool.get(receivedPlayerData.getId());

        if (playerData != null) {
            playerData.setMouseX(receivedPlayerData.getMouseX());
            playerData.setMouseY(receivedPlayerData.getMouseY());
            playerData.updateCanvasProperties(receivedPlayerData.getCanvasX(), receivedPlayerData.getCanvasY(),
                    receivedPlayerData.getCanvasHeight(), receivedPlayerData.getCanvasWidth());
        }
        return playerData;
    }
}
