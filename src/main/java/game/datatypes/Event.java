package game.datatypes;

public class Event {
    private String eventCommand;
    private double eventX;
    private double eventY;
    private String additionalText;
    
    public double getEventX() {
        return eventX;
    }
    
    public void setEventX(double eventX) {
        this.eventX = eventX;
    }
    
    public String getAdditionalText() {
        return additionalText;
    }
    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }
    public double getEventY() {
        return eventY;
    }
    
    public void setEventY(double eventY) {
        this.eventY = eventY;
    }
    
    public String getEventCommand() {
        return eventCommand;
    }
    
    public void setEventCommand(String eventCommand) {
        this.eventCommand = eventCommand;
    }
    
    
}
