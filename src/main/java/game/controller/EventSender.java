package game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import factory.EventBuilder;
import game.config.constant.BrokerPaths;
import game.datatype.Event;
import game.interfaces.Spawnable;

@Controller
public class EventSender {

    @Autowired
    private SimpMessagingTemplate template;    
    
    @Autowired
    EventBuilder eventBuilder;
    
    @SendTo(BrokerPaths.EVENT_BROKER)
    private void broadcastEvent(Event event){
        template.convertAndSend(BrokerPaths.EVENT_BROKER, event);
    }

    public void sendItemHitNotification(Spawnable item){
        Event event = eventBuilder.setEventXCoordinate(item.getX()).
                                    setEventYCoordinate(item.getY()).
                                    setEventCommand("PLAY_HIT_ANIM").
                                    build();
    
        broadcastEvent(event);
    }
    
    public void sendItemDestroyedNotification(Spawnable item){
        Event event = eventBuilder.setEventXCoordinate(item.getX()).
                setEventYCoordinate(item.getY()).
                setEventCommand("PLAY_EXPLOSION_ANIM").
                build();
        
        broadcastEvent(event);
    }
}
