package game.datatype;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.ShieldFactory;
import factory.ShipFactory;
import game.config.constant.AmmoType;
import game.config.constant.Bonuses;
import game.config.constant.CanvasConstants;
import game.config.constant.GameConfig;
import game.config.constant.ItemType;
import game.config.constant.ShipConfig;
import game.interfaces.Ammo;
import game.interfaces.Shield;
import game.interfaces.Ship;
import game.interfaces.Weapon;
import game.scheduler.AIMovementTimerTask;
import game.service.AISpawner;
import game.service.Spawner;

public class PlayerData {

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

    private boolean isAI;

    private boolean isAsteroid;

    private String shipType;

    private Map<Bonuses, Long> bonuses;

    public PlayerData(PlayerData player2) {
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
        this.setCoordinate(player2.getCoordinate());
        this.setPreviousAngle(this.getPreviousAngle());
        this.setCanvas(player2.getCanvas());
        this.setShipType(player2.getShipType());
    }

    public PlayerData(Long id, String name, ShipConfig shipConfig, AIDao aiDao) {
        this.name = name;
        this.id = id;
        this.isAI = aiDao.getIsAi();
        this.isAsteroid = aiDao.getIsAsteroid();

        this.spaceShip = ShipFactory.createShip(shipConfig);
        this.setShipType(shipConfig.getType());
        Spawner.spawn(this.getSpaceShip());

        this.getSpaceShip().setAngle(0.0d);
        this.mouseX = 0L;
        this.mouseY = 0L;

        this.setNewMousePointForAI();

        this.connectionId = 0L;
        this.getSpaceShip().resetHp();
        this.initWeapons();
        this.getSpaceShip().resetManeuverability();
        this.getSpaceShip().resetSpeed();
        this.score = 0l;
        
        if (shipConfig == ShipConfig.ASTEROID) {
            this.setInvulnerabilityCounter(0L);
        }

        if(this.isAI){
            this.respawnTime = 1L;
        } else {
            this.respawnTime = GameConfig.PLAYER_RESPAWN_TIME;
        }
        
        this.setCanvas(new Canvas(0, 0, CanvasConstants.CANVAS_HEIGHT, CanvasConstants.CANVAS_WIDTH));

        this.setAIMovementTimer();

        this.initBonuses();
    }

    public void updateCanvasProperties(long x, long y, long height, long width) {
        this.canvas.setX(x);
        this.canvas.setY(y);
        this.canvas.setHeight(height);
        this.canvas.setWidth(width);
    }

    public void kill() {
    	this.setNewMousePointForAI();
        Spawner.spawn(this.getSpaceShip());
        this.resetBonuses();
        this.inactivityCounter = 0;
        this.getSpaceShip().resetHp();
        this.invulnerabilityCounter = this.isAsteroid ? 0L : GameConfig.INVULN_CTR_MAX_VALUE;
        this.initWeapons();
        this.getSpaceShip().resetManeuverability();
        this.getSpaceShip().resetSpeed();
        this.score = 0L;
        this.getSpaceShip().initShield();
        
        if(this.isAI){
            this.respawnTime = 1L;
        } else {
            this.respawnTime = GameConfig.PLAYER_RESPAWN_TIME;            
        }
    }

    public long getActualWeaponAmmoCount() {
        return this.getSpaceShip().getActualAmmoCount();
    }

    public void decreasAmmoForPlayerWeapon() {
    	this.getSpaceShip().decreaseAmmoCount();
    }

    public void startShootingRateCooldownEffect() {
        getWeapon().startCooldownEffect();
    }

    public List<Ammo> createAmmoWithPlayerWeapon() {
        return getWeapon().createAmmo(this);
    }

    public boolean canShootWeapon() {
        return getSpaceShip().canShoot() && this.isSpawned();
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

    public boolean isSpawned() {
        if (this.respawnTime == 0L)
            return true;
        return false;
    }

    public void initWeapons() {
        this.getSpaceShip().initWeaponsAndAmmo();
    }

    public void decreasePlayerRespawnTime() {
        if (this.respawnTime > 0L)
            this.respawnTime--;
    }

    public void increaseInactivityCounter() {
        inactivityCounter++;
    }

    public void increaseShieldPower() {
        getShield().increaseShieldPower();
    }

    public double decreaseHp(double value) {
        double hpRemaining = getSpaceShip().decreaseHp(value);

        return hpRemaining;
    }

    public void setShieldProtection(long value) {
        getShield().setProtection(value);
    }

    public void decreaseInvulnerabilityCounter(long value) {
        if (invulnerabilityCounter > 0L) {
            invulnerabilityCounter -= value;
            if (invulnerabilityCounter < 0L) {
                invulnerabilityCounter = 0L;
            }
        }
    }

    public void increaseSpeed(double value) {
        double speed = this.getSpaceShip().getSpeed() + value;
        this.setSpeed(speed);
    }

    /* Getters/setters and constructors */

    public String getName() {
        return name;
    }

    public ShipConfig getShipConfig() {
        return this.getSpaceShip().getShipConfig();
    }

    public void setShipConfig(ShipConfig shipConfig) {
        this.getSpaceShip().setShipConfig(shipConfig);
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
        Ship spaceShipToStore = ShipFactory.createShip(spaceShip.getShipConfig());

        spaceShipToStore.setColor(spaceShip.getColor());
        spaceShipToStore.setCoordinate(spaceShip.getCoordinate());
        spaceShipToStore.setHp(spaceShip.getHp());
        spaceShipToStore.setManeuverability(spaceShip.getManeuverability());
        spaceShipToStore.setMaxSpeed(spaceShip.getMaxSpeed());
        spaceShipToStore.setShield(spaceShip.getShield());
        spaceShipToStore.setAngle(spaceShip.getAngle());
        spaceShipToStore.setShipConfig(spaceShip.getShipConfig());
        spaceShipToStore.setSpeed(spaceShip.getSpeed());
        spaceShipToStore.setWeapon(spaceShip.getWeapon());
        spaceShipToStore.setCarriage(spaceShip.getCarriage());

        return spaceShipToStore;
    }

    public void setInvulnerabilityCounter(long invulnerabilityCounter) {
        this.invulnerabilityCounter = invulnerabilityCounter;
    }

    public Double getShipAngle() {
        return this.getSpaceShip().getAngle();
    }

    public void setShipAngle(Double shipAngle) {
        this.getSpaceShip().setAngle(shipAngle);
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

    public double getY() {
        return getSpaceShip().getY();
    }

    public Point2D getCoordinate() {
        return getSpaceShip().getCoordinate();
    }

    public void setCoordinate(Point2D coordinate) {
        this.getSpaceShip().setCoordinate(coordinate);
    }

    public void setCoordinate(double x, double y) {
        this.getSpaceShip().setCoordinate(x, y);
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

    public Long getMaxHp() {
        return this.getSpaceShip().getMaxHp();
    }

    public boolean isInvulnerable() {
        return this.getInvulnerabilityCounter() > 0L;
    }

    public Weapon getWeapon() {
        return this.getSpaceShip().getWeapon();
    }

    public void setWeapon(Weapon weapon) {
        this.getSpaceShip().setWeapon(weapon);
    }

    public void addWeapon(Weapon weapon) {
    	weapon.applyBonuses(this);
        this.getSpaceShip().addWeapon(weapon);
    }

    public void selectWeapon(int index) {
        this.getSpaceShip().selectWeapon(index);
    }

    public double getManeuverability() {
        return this.getSpaceShip().getManeuverability();
    }

    public void setManeuverability(double maneuverability) {
        this.getSpaceShip().setManeuverability(maneuverability);
    }

    public void increaseManeuverablility(double value) {
        double maneuverability = this.getSpaceShip().getManeuverability() - value;
        if (maneuverability < 1.0d)
            maneuverability = 1.0d;
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

    public void increaseScore(long value) {
        score += value;
    }

    public String toString() {
        return this.name + " " + this.mouseX + " " + this.mouseY;
    }

    public boolean getIsAI() {
        return this.isAI;
    }

    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    public void setNewMousePointForAI() {
        if (this.isAI) {
            Point2D newrandomPoint = AISpawner.generateRandomCoordinate();
            this.setMouseX((long) newrandomPoint.getX());
            this.setMouseY((long) newrandomPoint.getY());
        }
    }

    private void setAIMovementTimer() {
        if (this.isAI && !this.isAsteroid) {
            AIMovementTimerTask task = new AIMovementTimerTask(this);
        }
    }

    private void initBonuses() {
        this.bonuses = new HashMap<Bonuses, Long>();
        this.resetBonuses();
    }

    private void resetBonuses() {
        this.bonuses.put(Bonuses.DAMAGE, 0L);
        this.bonuses.put(Bonuses.RATE_OF_FIRE, 0L);
    }

    public Map<Bonuses, Long> getBonuses() {
        return this.bonuses;
    }
    
    public long getDamageBonus() {
    	return this.bonuses.get(Bonuses.DAMAGE);
    }
    
    public long getRateOfFireBonus() {
    	return this.bonuses.get(Bonuses.RATE_OF_FIRE);
    }

    public void increaseBonus(Bonuses bonus, long value) {
        this.bonuses.put(bonus, this.bonuses.get(bonus) + value);
    }

    public boolean getIsAsteroid() {
        return this.isAsteroid;
    }

    public void setIsAsteroid(boolean isAsteroid) {
        this.isAsteroid = isAsteroid;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
    
	public double getHitRadius() {
		return this.getSpaceShip().getHitRadius();
	}

	public void setHitRadius(double hitRadius) {
		this.getSpaceShip().setHitRadius(hitRadius);
	}
}