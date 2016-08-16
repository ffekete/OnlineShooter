package game.datatypes;

public class Event {
    private String eventCommand;
    private double eventX;
    private double eventY;
    private String additionalText;
    
    public Event(String eventCommand, double eventX, double eventY, String additionalText) {
        super();
        this.eventCommand = eventCommand;
        this.eventX = eventX;
        this.eventY = eventY;
        this.additionalText = additionalText;
    }

    public String getEventCommand() {
        return eventCommand;
    }

    public double getEventX() {
        return eventX;
    }

    public double getEventY() {
        return eventY;
    }

    public String getAdditionalText() {
        return additionalText;
    }
    
    
}
