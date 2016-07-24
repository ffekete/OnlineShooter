package controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import config.BrokerPaths;
import config.EndpointPaths;

@Controller
public class PlayerRegisterController {

	@MessageMapping(EndpointPaths.REGISTER_PLAYER)
	@SendTo(BrokerPaths.PLAYER_REGITERED_STATUS)
	public void registerNewPlayer(){
		
	}
}
