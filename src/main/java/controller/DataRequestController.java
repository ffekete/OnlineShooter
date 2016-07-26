package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import config.BrokerPaths;
import config.EndpointPaths;
import datahandler.PlayerPool;
import model.PlayerData;
import model.SentPlayerData;
import transformer.PlayerDataToSentPlayerDataTransformer;

/** Clients can poll REQUEST_PLAYER_DATA channel to receive server-side information about the given player. */
@Controller
public class DataRequestController {
	
	@Autowired
	PlayerDataToSentPlayerDataTransformer playerDataToSentPlayerDataTransformer;
	
	@Autowired
	private PlayerPool playerPool;
	
	private SentPlayerData handleRequest(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return playerDataToSentPlayerDataTransformer.transform(currentPlayer);
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
