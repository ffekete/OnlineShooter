package game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import game.config.constant.EndpointPaths;
import game.datatype.PlayerData;
import game.datatype.ReceivedPlayerData;
import game.transformer.ReceivedPlayerDataToPlayerDataTransformer;

@Controller
public class PlayerDataUpdateHandler implements ControllerBase {

    @Autowired
    ReceivedPlayerDataToPlayerDataTransformer receivedPlayerDataToPlayerDataTransformer;

    @MessageMapping(EndpointPaths.UPDATE_PLAYER_DATA)
    public void updateplayerData(ReceivedPlayerData receivedPlayerData) {
        if (receivedPlayerData != null) {
            PlayerData playerData = receivedPlayerDataToPlayerDataTransformer.transform(receivedPlayerData);
            if (playerData == null) {
                System.out.println("Failed to update player data: " + receivedPlayerData.getId());
            }
        } else {
            System.out.println("!!!Error: Player data received with null reference. ");
        }
    }
}
