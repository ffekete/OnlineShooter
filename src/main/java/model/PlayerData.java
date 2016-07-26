package model;

public class PlayerData {
	private String name;
	private Long id;
	private Long x;
	private Long y;
	
	private int inactivityCounter = 0;
	
	private Long mouseX;
	private Long mouseY;
	
	private Double shipAngle;
	
	public void increaseInactivityCounter(){
		inactivityCounter++;
	}
	
	/* Getters/setters and constructors */
	public String getName() {
		return name;
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
	public Long getX() {
		return x;
	}
	public void setX(Long x) {
		this.x = x;
	}
	public Long getY() {
		return y;
	}
	public void setY(Long y) {
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
		this.x = 0L;
		this.y = 0L;
		this.mouseX = 0L;
		this.mouseY = 0L;
		this.shipAngle = 0.0d;
	}
	
	public String toString(){
		return this.name + " " + this.mouseX + " " + this.mouseY;
	}
}
