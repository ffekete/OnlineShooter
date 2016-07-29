package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import config.BrokerPaths;
import model.Event;
import model.PlayerData;

@Controller
public class EventSender {

	@Autowired
    private SimpMessagingTemplate template;	
	
	@SendTo(BrokerPaths.EVENT_BROKER)
	private void broadcastEvent(Event event){
		template.convertAndSend(BrokerPaths.EVENT_BROKER, event);
	}
	
	public void sendPlayerDeathNotification(PlayerData player){
		Event event = new Event();
		event.setEvent_x(player.getX());
		event.setEvent_y(player.getY());
		event.setEventCommand("PLAY_EXPLOSION_ANIM");
		event.setAdditionalText(player.getName());
		
		broadcastEvent(event);
	}
}
