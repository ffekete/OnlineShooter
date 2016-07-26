package model;

public class PlayerData {
	private String name;
	private Long id;
	private double x;
	private double y;
	
	private int inactivityCounter = 0;
	
	private Long mouseX;
	private Long mouseY;
	
	private Double shipAngle;
	
	private Long connectionId;
	
	public void increaseInactivityCounter(){
		inactivityCounter++;
	}
	
	/* Getters/setters and constructors */
		
	public String getName() {
		return name;
	}
	public Double getShipAngle() {
		return shipAngle;
	}

	public void setShipAngle(Double shipAngle) {
		this.shipAngle = shipAngle;
	}

	public Long getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(Long connectionId) {
		this.connectionId = connectionId;
	}

	public int getInactivityCounter() {
		return inactivityCounter;
	}
	public void setInactivityCounter(int inactivityCounter) {
		this.inactivityCounter = inactivityCounter;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Long getMouseX() {
		return mouseX;
	}
	public void setMouseX(Long mouseX) {
		this.mouseX = mouseX;
	}
	public Double getAngle() {
		return shipAngle;
	}
	public void setAngle(Double angle) {
		this.shipAngle = angle;
	}
	public Long getMouseY() {
		return mouseY;
	}
	public void setMouseY(Long mouseY) {
		this.mouseY = mouseY;
	}
	
	public PlayerData() {
		super();
	}
	
	public PlayerData(Long id, String name) {
		this.name = name;
		this.id = id;
		this.x = 0.0d;
		this.y = 0.0d;
		this.mouseX = 0L;
		this.mouseY = 0L;
		this.shipAngle = 0.0d;
		this.connectionId = 0L;
	}
	
	public String toString(){
		return this.name + " " + this.mouseX + " " + this.mouseY;
	}
}
