package game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import game.config.BrokerPaths;

@Controller
public class MessageSender {

	@Autowired
    private SimpMessagingTemplate template;	
	
	@SendTo(BrokerPaths.MESSAGE_BROKER)
	public void broadCastMessage(String message){
		template.convertAndSend(BrokerPaths.MESSAGE_BROKER, message);
	}
}
