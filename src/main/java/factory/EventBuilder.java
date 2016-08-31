package factory;

import org.springframework.stereotype.Component;

import game.datatype.Event;

@Component
public class EventBuilder {

    private String eventCommand = null;
    private Double eventX = null;
    private Double eventY = null;
    private String additionalText = null;

    public EventBuilder setEventXCoordinate(double x) {
        this.eventX = x;
        return this;
    }

    public EventBuilder setEventYCoordinate(double y) {
        this.eventY = y;
        return this;
    }

    public EventBuilder setEventCommand(String command) {
        this.eventCommand = command;
        return this;
    }

    public EventBuilder setEventAdditionalText(String text) {
        this.additionalText = text;
        return this;
    }

    public Event build() {
        if (eventCommand == null) {
            this.clean();
            throw new NullPointerException("(E): Event command cannot be null!");
        }

        if (eventX == null) {
            this.clean();
            throw new NullPointerException("(E): Event x coordinate cannot be null!");
        }

        if (eventY == null) {
            this.clean();
            throw new NullPointerException("(E): Event y coordinate cannot be null!");
        }

        Event event = new Event(eventCommand, eventX, eventY, additionalText);

        /* cleaning up */
        this.clean();

        return event;
    }

    private void clean() {
        eventCommand = null;
        eventX = null;
        eventY = null;
    }
}
