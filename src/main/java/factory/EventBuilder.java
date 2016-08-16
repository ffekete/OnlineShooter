package factory;

import org.springframework.stereotype.Component;

import game.datatypes.Event;

@Component
public class EventBuilder {

    private String eventCommand;
    private double eventX;
    private double eventY;
    private String additionalText;
    
    public EventBuilder setEventXCoordinate(double x){
        this.eventX = x;
        return this;
    }
}
