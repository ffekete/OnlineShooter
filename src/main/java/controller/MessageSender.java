package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageSender {

	@Autowired
    private SimpMessagingTemplate template;	
	
	@SendTo("/messages")
	public void broadCastMessage(String message){
		template.convertAndSend("/messages", message);
	}
}
