package game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import game.config.constants.BrokerPaths;
import game.config.constants.EndpointPaths;
import game.datatypes.PlayerData;
import game.interfaces.PlayerPoolMap;
import game.model.SentPlayerData;
import game.transformer.PlayerDataToSentPlayerDataTransformer;

@Controller
public class DataRequestController {
    
    @Autowired
    PlayerDataToSentPlayerDataTransformer playerDataToSentPlayerDataTransformer;
    
    @Autowired
    private PlayerPoolMap<Long, PlayerData> playerPool;
    
    private SentPlayerData handleRequest(Long id){
        if(id != null){
            PlayerData currentPlayer = playerPool.get(id);
            if(currentPlayer != null){
                playerPool.resetInactivityOfPlayer(id);
                return playerDataToSentPlayerDataTransformer.transform(currentPlayer);
            }
        }
        return null;
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "0")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "0")
    public SentPlayerData getUserData_0(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "1")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "1")
    public SentPlayerData getUserData_1(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "2")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "2")
    public SentPlayerData getUserData_2(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "3")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "3")
    public SentPlayerData getUserData_3(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "4")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "4")
    public SentPlayerData getUserData_4(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "5")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "5")
    public SentPlayerData getUserData_5(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "6")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "6")
    public SentPlayerData getUserData_6(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "7")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "7")
    public SentPlayerData getUserData_7(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "8")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "8")
    public SentPlayerData getUserData_8(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "9")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "9")
    public SentPlayerData getUserData_9(Long id){
        return handleRequest(id);
    }
    
    @MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "10")
    @SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "10")
    public SentPlayerData getUserData_10(Long id){
        return handleRequest(id);
    }
}
