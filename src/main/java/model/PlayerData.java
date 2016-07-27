package model;

import org.springframework.beans.factory.annotation.Autowired;

import config.GameConfig;
import service.PlayerSpawner;

public class PlayerData {
	
	@Autowired
	private PlayerSpawner playerSpawner = new PlayerSpawner();
	
	private String name;
	private long id;
	private double x;
	private double y;
	
	private int inactivityCounter = 0;
	
	private long invulnerabilityCounter = GameConfig.INVULN_CTR_MAX_VALUE;
	
	private long mouseX;
	private long mouseY;
	
	private long hp;
	
	private Double shipAngle;
	
	private long connectionId;
	
	private long rateOfFire;
	
	private long rateOfFireCooldown = 0L;
	
	public boolean canShoot(){
		return rateOfFireCooldown < 1L;
	}
	
	public void startShootingRateCooldownEffect(){
		rateOfFireCooldown = this.rateOfFire;
	}
	
	public void decreaseRateOfFireCooldownValue(long value){
		if(rateOfFireCooldown > 0L)
		{
			rateOfFireCooldown -= value;
			if(rateOfFireCooldown < 0L) rateOfFireCooldown = 0L;
		}
	}
	
	public void increaseInactivityCounter(){
		inactivityCounter++;
	}
	
	public void kill(){
		playerSpawner.spawn(this); 
		inactivityCounter = 0;
		hp = GameConfig.SHIP_INITIAL_HP;
		invulnerabilityCounter = GameConfig.INVULN_CTR_MAX_VALUE;
		rateOfFire = GameConfig.DEF_RATE_OF_FIRE;
	}
	
	public void decreaseHp(long value){
		hp -= value;
		if(hp < 0L ) {
			hp = 0L;
		}
	}
	
	public void decreaseInvulnerabilityCounter(long value){
		if(invulnerabilityCounter > 0L){
			invulnerabilityCounter -= value;
			if(invulnerabilityCounter < 0L){
				invulnerabilityCounter = 0L;
			}
		}
	}
	
	/* Getters/setters and constructors */
	public String getName() {
		return name;
	}
	
	public long getRateOfFire() {
		return rateOfFire;
	}

	public void setRateOfFire(long rateOfFire) {
		this.rateOfFire = rateOfFire;
	}

	public long getInvulnerabilityCounter() {
		return invulnerabilityCounter;
	}

	public void setInvulnerabilityCounter(long invulnerabilityCounter) {
		this.invulnerabilityCounter = invulnerabilityCounter;
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
	
	public Long getHp() {
		return hp;
	}

	public void setHp(Long hp) {
		this.hp = hp;
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
		this.hp = GameConfig.SHIP_INITIAL_HP;
		this.rateOfFire = GameConfig.DEF_RATE_OF_FIRE;
	}
	
	public String toString(){
		return this.name + " " + this.mouseX + " " + this.mouseY;
	}
}
