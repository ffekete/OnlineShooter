package controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import config.BrokerPaths;
import config.EndpointPaths;
import model.PlayerData;

@Controller
public class DataRequestController {
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA)
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA)
	public PlayerData getUserData(Long id){
		return null;
	}
}
