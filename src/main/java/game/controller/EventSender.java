package game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import game.config.BrokerPaths;
import game.datatypes.Event;
import game.interfaces.Spawnable;

@Controller
public class EventSender {

    @Autowired
    private SimpMessagingTemplate template;    
    
    @SendTo(BrokerPaths.EVENT_BROKER)
    private void broadcastEvent(Event event){
        template.convertAndSend(BrokerPaths.EVENT_BROKER, event);
    }

    public void sendItemHitNotification(Spawnable item){
        Event event = new Event();
        event.setEventX(item.getX());
        event.setEventY(item.getY());
        event.setEventCommand("PLAY_HIT_ANIM");
    
        broadcastEvent(event);
    }
    
    public void sendItemDestroyedNotification(Spawnable item){
        Event event = new Event();
        event.setEventX(item.getX());
        event.setEventY(item.getY());
        event.setEventCommand("PLAY_EXPLOSION_ANIM");
        
        broadcastEvent(event);
    }
}
