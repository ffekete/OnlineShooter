package model;

import config.CanvasConstants;
import config.GameConfig;
import config.Physics;
import config.ShieldId;
import config.WeaponId;
import factory.ShieldFactory;
import factory.WeaponFactory;
import interfaces.Shield;
import interfaces.Spawnable;
import interfaces.Weapon;
import service.Spawner;

public class PlayerData implements Spawnable{
	private String name;
	private long id;
	private double x;
	private double y;
	
	private String color;
	
	private Canvas canvas;
	
	private long respawnTime;
	
	private Shield shield;
	
	private double previousAngle = 0.0d;
	
	private int inactivityCounter = 0;
	
	private long invulnerabilityCounter = GameConfig.INVULN_CTR_MAX_VALUE;
	
	private long mouseX;
	private long mouseY;
	
	private long score;
	
	private long hp;
	
	private Double shipAngle;
	
	private long connectionId;
	
	private Weapon weapon;
	
	private double maneuverability;
	
	private double speed;
	
	private double maxSpeed;
	
	public PlayerData(PlayerData player2){
		this.setAngle(player2.getAngle());
		this.setConnectionId(player2.getConnectionId());
		this.setHp(player2.getHp());
		this.setId(player2.getId());
		this.setInactivityCounter(player2.getInactivityCounter());
		this.setInvulnerabilityCounter(player2.getInvulnerabilityCounter());
		this.setManeuverability(player2.getManeuverability());
		this.setMouseX(player2.getMouseX());
		this.setMouseY(player2.getMouseY());
		this.setName(player2.getName());
		this.setScore(player2.getScore());
		this.setShield(player2.getShield());
		this.setShipAngle(player2.getShipAngle());
		this.setSpeed(player2.getSpeed());
		this.setWeapon(player2.getWeapon());
		this.setX(player2.getX());
		this.setY(player2.getY());
		this.setPreviousAngle(this.getPreviousAngle());
		this.setCanvas(player2.getCanvas());
	}
	
	public PlayerData(Long id, String name) {
		this.name = name;
		this.id = id;
		
		Spawner.spawn(this);
		
		this.mouseX = 0L;
		this.mouseY = 0L;
		this.shipAngle = 0.0d;
		this.connectionId = 0L;
		this.hp = GameConfig.SHIP_INITIAL_HP;
		this.weapon = new WeaponFactory().createWeapon(WeaponId.MACHINEGUN);
		this.maneuverability = Physics.SMOOTHING;
		this.speed = GameConfig.SHIP_INIT_SPEED;
		this.maxSpeed = GameConfig.SHIP_INIT_SPEED;
		this.score = 0l;
		this.shield = new ShieldFactory().createShield(ShieldId.NORMAL_SHIELD);
		this.respawnTime = GameConfig.PLAYER_RESPAWN_TIME;
		this.setCanvas(new Canvas(0, 0, CanvasConstants.CANVAS_HEIGHT, CanvasConstants.CANVAS_WIDTH));
		
	}
	
	public void updateCanvasProperties(long x, long y, long height, long width){
		this.canvas.setX(x);
		this.canvas.setY(y);
		this.canvas.setHeight(height);
		this.canvas.setWidth(width);
	}
	
	public void kill(){
		Spawner.spawn(this); 
		inactivityCounter = 0;
		hp = GameConfig.SHIP_INITIAL_HP;
		invulnerabilityCounter = GameConfig.INVULN_CTR_MAX_VALUE;
		this.weapon = new WeaponFactory().createWeapon(WeaponId.MACHINEGUN);
		this.maneuverability = Physics.SMOOTHING;
		this.speed = GameConfig.SHIP_INIT_SPEED;
		this.maxSpeed = GameConfig.SHIP_INIT_SPEED;
		this.score = 0l;
		this.shield = new ShieldFactory().createShield(ShieldId.NORMAL_SHIELD);	
		this.respawnTime = GameConfig.PLAYER_RESPAWN_TIME;
	}
	
	
	
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public long getRespawnTime() {
		return respawnTime;
	}

	public boolean isSpawned(){
		if(this.respawnTime == 0L) return true;
		return false;
	}
	
	public void initWeapon(){
		this.weapon = new WeaponFactory().createWeapon(WeaponId.MACHINEGUN);
	}
	
	public void decreasePlayerRespawnTime(){
		if(this.respawnTime > 0L)
			this.respawnTime--;
	}
	
	public void increaseInactivityCounter(){
		inactivityCounter++;
	}
	
	public void increaseShieldPower(){
		getShield().increaseShieldPower();
	}
	
	public long decreaseHp(long value){
		
		long shieldProtection = getShield().getProtection();
		long hpAfterReduction; 
				
		if(shieldProtection > 0L){
			getShield().decreaseProtection(value);
			hpAfterReduction = hp;
		}
		else
		{
			hp -= value;
			hpAfterReduction = hp;
			if(hp < 1L ) {
				kill();
			}
		}
		return hpAfterReduction; 
	}
	
	public void decreaseInvulnerabilityCounter(long value){
		if(invulnerabilityCounter > 0L){
			invulnerabilityCounter -= value;
			if(invulnerabilityCounter < 0L){
				invulnerabilityCounter = 0L;
			}
		}
	}
	
	public void increaseSpeed(double value){
		this.speed += value;
	}
	
	/* Getters/setters and constructors */
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getPreviousAngle() {
		return previousAngle;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setPreviousAngle(double previousAngle) {
		this.previousAngle = previousAngle;
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
		if(this.hp > GameConfig.SHIP_MAX_HP) this.hp = GameConfig.SHIP_MAX_HP; 
	}

	public boolean isInvulnerable(){
		return this.getInvulnerabilityCounter() > 0L;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public double getManeuverability() {
		return maneuverability;
	}

	public void setManeuverability(double maneuverability) {
		this.maneuverability = maneuverability;
	}

	public void increaseManeuverablility(double value){
		this.maneuverability -= value;
		if(this.maneuverability < 1.0d) this.maneuverability = 1.0d;
	}
	
	public Shield getShield() {
		return shield;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public void increaseScore(long value){
		score += value;
	}
	
	public String toString(){
		return this.name + " " + this.mouseX + " " + this.mouseY;
	}
}
