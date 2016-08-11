package game.datatypes;

import java.util.List;

import factory.ShieldFactory;
import factory.ShipFactory;
import game.config.CanvasConstants;
import game.config.GameConfig;
import game.config.ShieldId;
import game.interfaces.Bullet;
import game.interfaces.Shield;
import game.interfaces.Ship;
import game.interfaces.Spawnable;
import game.interfaces.Weapon;
import game.service.Spawner;

public class PlayerData implements Spawnable{
    
    private Ship spaceShip;
    
    private String name;
    private long id;
    
    private Canvas canvas;
    
    private long respawnTime;

    private double previousAngle = 0.0d;
    
    private int inactivityCounter = 0;
    
    private long invulnerabilityCounter = GameConfig.INVULN_CTR_MAX_VALUE;
    
    private long mouseX;
    private long mouseY;
    
    private long score;
    
    private long connectionId;
    
    public PlayerData(PlayerData player2){
        this.spaceShip = this.cloneSpaceShip(player2.getSpaceShip());
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
    
    public PlayerData(Long id, String name, String shipType) {
        this.name = name;
        this.id = id;
        
        this.spaceShip = ShipFactory.createShip(shipType);
        
        Spawner.spawn(this.getSpaceShip());
        
        this.mouseX = 0L;
        this.mouseY = 0L;
        this.getSpaceShip().setShipAngle(0.0d);
        this.connectionId = 0L;
        this.getSpaceShip().resetHp();
        this.initWeapon();
        this.getSpaceShip().resetManeuverability();
        this.getSpaceShip().resetSpeed();
        this.score = 0l;
        this.getSpaceShip().setShield(ShieldFactory.createShield(ShieldId.NORMAL_SHIELD));
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
        Spawner.spawn(this.getSpaceShip()); 
        this.inactivityCounter = 0;
        this.getSpaceShip().resetHp();
        this.invulnerabilityCounter = GameConfig.INVULN_CTR_MAX_VALUE;
        this.initWeapon();
        this.getSpaceShip().resetManeuverability();
        this.getSpaceShip().resetSpeed();
        this.score = 0l;
        this.getSpaceShip().setShield(ShieldFactory.createShield(ShieldId.NORMAL_SHIELD));
        this.respawnTime = GameConfig.PLAYER_RESPAWN_TIME;
    }
    
    public long getActualWeaponAmmo(){
        return getWeapon().getAmmo();
    }
    
    public void decreasAmmoForPlayerWeapon(long amount){
        getWeapon().decreaseAmmo(amount);
    }
    
    public void startShootingRateCooldownEffect(){
        getWeapon().startShootingRateCooldownEffect();
    }
    
    public List<Bullet> createBulletWithPlayerWeapon(){
        return getWeapon().createBullet(this);
    }
    
    public boolean canShootWeapon(){
        return getWeapon().canShoot();
    }
    
    public double getScreenHalfWidth() {
        return canvas.getHalfWidth();
    }
    
    public double getScreenHalfHeight() {
        return canvas.getHalfHeight();
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
        this.getSpaceShip().initWeapon();
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
        long hpRemaining = this.getSpaceShip().decreaseHp(value);
        
        if(hpRemaining < 1L){
            this.kill();
        }
        
        return hpRemaining;
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
        double speed = this.getSpaceShip().getSpeed() + value;
        this.setSpeed(speed);
    }
    
    /* Getters/setters and constructors */
    
    public String getName() {
        return name;
    }
    
    public String getShipType() {
        return this.getSpaceShip().getShipType();
    }

    public void setShipType(String shipType) {
        this.getSpaceShip().setShipType(shipType);
    }

    public String getColor() {
        return this.getSpaceShip().getColor();
    }

    public void setColor(String color) {
        this.getSpaceShip().setColor(color);
    }

    public double getSpeed() {
        return this.getSpaceShip().getSpeed();
    }

    public void setSpeed(double speed) {
        this.getSpaceShip().setSpeed(speed);
    }

    public double getPreviousAngle() {
        return previousAngle;
    }

    public double getMaxSpeed() {
        return getSpaceShip().getMaxSpeed();
    }

    public void setPreviousAngle(double previousAngle) {
        this.previousAngle = previousAngle;
    }

    public long getInvulnerabilityCounter() {
        return invulnerabilityCounter;
    }

    public Ship getSpaceShip() {
        return spaceShip;
    }

    public void setSpaceShip(Ship spaceShip) {
        this.spaceShip = spaceShip;
    }
    
    public Ship cloneSpaceShip(Ship spaceShip) {
        Ship spaceShipToStore = ShipFactory.createShip(spaceShip.getShipType());
        
        spaceShipToStore.setColor(spaceShip.getColor());
        spaceShipToStore.setCoordinates(spaceShip.getCoordinates().getX(), spaceShip.getCoordinates().getY());
        spaceShipToStore.setHp(spaceShip.getHp());
        spaceShipToStore.setManeuverability(spaceShip.getManeuverability());
        spaceShipToStore.setMaxSpeed(spaceShip.getMaxSpeed());
        spaceShipToStore.setShield(spaceShip.getShield());
        spaceShipToStore.setShipAngle(spaceShip.getShipAngle());
        spaceShipToStore.setShipType(spaceShip.getShipType());
        spaceShipToStore.setSpeed(spaceShip.getSpeed());
        spaceShipToStore.setWeapon(spaceShip.getWeapon());
        
        return spaceShipToStore;
    }
    

    public void setInvulnerabilityCounter(long invulnerabilityCounter) {
        this.invulnerabilityCounter = invulnerabilityCounter;
    }

    public Double getShipAngle() {
        return this.getSpaceShip().getShipAngle();
    }

    public void setShipAngle(Double shipAngle) {
        this.getSpaceShip().setShipAngle(shipAngle);
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
        return getSpaceShip().getX();
    }
    
    public void setX(double x) {
        this.getSpaceShip().setX(x);
    }
    
    public double getY() {
        return getSpaceShip().getY();
    }
    public void setY(double y) {
        this.getSpaceShip().setY(y);
    }
    public Long getMouseX() {
        return mouseX;
    }
    public void setMouseX(Long mouseX) {
        this.mouseX = mouseX;
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
        return this.getSpaceShip().getHp();
    }

    public void setHp(long hp) {
        this.getSpaceShip().setHp(hp);
    }

    public boolean isInvulnerable(){
        return this.getInvulnerabilityCounter() > 0L;
    }
    
    public Weapon getWeapon() {
        return this.getSpaceShip().getWeapon();
    }

    public void setWeapon(Weapon weapon) {
        this.getSpaceShip().setWeapon(weapon);
    }

    public double getManeuverability() {
        return this.getSpaceShip().getManeuverability();
    }

    public void setManeuverability(double maneuverability) {
        this.getSpaceShip().setManeuverability(maneuverability);
    }

    public void increaseManeuverablility(double value){
        double maneuverability = this.getSpaceShip().getManeuverability() - value;
        if(maneuverability < 1.0d) maneuverability = 1.0d;
        this.setManeuverability(maneuverability);
    }
    
    public Shield getShield() {
        return this.getSpaceShip().getShield();
    }

    public void setShield(Shield shield) {
        this.getSpaceShip().setShield(shield);
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
