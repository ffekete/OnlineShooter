package model;

public class Event {
	private String eventCommand;
	private double event_x;
	private double event_y;
	private String additionalText;
	
	public double getEvent_x() {
		return event_x;
	}
	
	public void setEvent_x(double event_x) {
		this.event_x = event_x;
	}
	
	public String getAdditionalText() {
		return additionalText;
	}
	public void setAdditionalText(String additionalText) {
		this.additionalText = additionalText;
	}
	public double getEvent_y() {
		return event_y;
	}
	
	public void setEvent_y(double event_y) {
		this.event_y = event_y;
	}
	
	public String getEventCommand() {
		return eventCommand;
	}
	
	public void setEventCommand(String eventCommand) {
		this.eventCommand = eventCommand;
	}
	
	
}
