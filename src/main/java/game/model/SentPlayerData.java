package game.model;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

import game.config.constant.AmmoType;
import game.datatype.PlayerData;
import game.interfaces.Ammo;
import game.interfaces.SpawnableItem;
import game.interfaces.Weapon;

public class SentPlayerData {
    private Long id;
    private Point2D coordinate;

    List<String> scores;

    private long respawnTime;

    private String color;

    private String shipType;

    private long score;

    private List<SpawnableItem> items;

    private Double shipAngle;

    private Long connectionId;

    private double shipHp;

    private double shipMaxHp;

    private boolean invulnerable;

    private double shieldAmount;

    private double maxShieldAmount;

    private boolean isAI;

    private boolean isAsteroid;

    Weapon weapon;

    List<Weapon> weapons;
	
	Map<AmmoType, Long> ammoCount;

    List<PlayerData> visiblePlayers;

    List<Ammo> visibleAmmo;

    public Long getId() {
        return id;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public Map<AmmoType, Long> getAmmoCount() {
		return ammoCount;
	}

	public void setAmmoCount(Map<AmmoType, Long> ammoCount) {
		this.ammoCount = ammoCount;
	}

	public boolean isInvulnerable() {
        return invulnerable;
    }

    public long getScore() {
        return score;
    }

    public String getShipType() {
        return this.shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public List<SpawnableItem> getItems() {
        return items;
    }

    public void setItems(List<SpawnableItem> items) {
        this.items = items;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getShipHp() {
        return shipHp;
    }

    public void setShipHp(double shipHp) {
        this.shipHp = shipHp;
    }

    public double getShipMaxHp() {
        return this.shipMaxHp;
    }

    public void setShipMaxHp(Long shipMaxHp) {
        this.shipMaxHp = shipMaxHp;
    }

    public double getMaxShieldAmount() {
        return maxShieldAmount;
    }

    public void setMaxShieldAmount(double maxShieldAmount) {
        this.maxShieldAmount = maxShieldAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getScores() {
        return scores;
    }

    public void setScores(List<String> scores) {
        this.scores = scores;
    }

    public void setCoordinate(double x, double y) {
        this.coordinate = new Point2D.Double(x, y);
    }

    public void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    public double getX() {
        return this.coordinate.getX();
    }

    public void setX(double x) {
        this.setCoordinate(x, this.coordinate.getY());
    }

    public long getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(long respawnTime) {
        this.respawnTime = respawnTime;
    }

    public double getY() {
        return this.coordinate.getY();
    }

    public void setY(double y) {
        this.setCoordinate(this.coordinate.getX(), y);
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

    public List<Ammo> getVisibleAmmo() {
        return visibleAmmo;
    }

    public List<PlayerData> getVisiblePlayers() {
        return visiblePlayers;
    }

    public double getShieldAmount() {
        return shieldAmount;
    }

    public void setShieldAmount(double shieldAmount) {
        this.shieldAmount = shieldAmount;
    }

    public void setVisiblePlayers(List<PlayerData> visiblePlayers) {
        this.visiblePlayers = visiblePlayers;
    }

    public void setVisibleAmmo(List<Ammo> visibleAmmo) {
        this.visibleAmmo = visibleAmmo;
    }

    public boolean getIsAI() {
        return isAI;
    }

    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    public boolean getIsAsteroid() {
        return this.isAsteroid;
    }

    public void setIsAsteroid(boolean isAsteroid) {
        this.isAsteroid = isAsteroid;
    }
}
