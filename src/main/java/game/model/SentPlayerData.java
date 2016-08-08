package game.model;

import java.util.List;

import game.interfaces.Bullet;
import game.interfaces.SpawnableItem;
import game.interfaces.Weapon;

public class SentPlayerData {
	private Long id;
	private double x;
	private double y;
	
	List<String> scores;
	
	private long respawnTime;
	
	private String color;
	
	private String shipType;
	
	private long score;
	
	private List<SpawnableItem> items;
	
	private Double shipAngle;
	
	private Long connectionId;
	
	private Long shipHp;
	
	private boolean invulnerable;
	
	private long shieldAmount;
	
	private long maxShieldAmount;
	
	Weapon weapon;
	
	List<PlayerData> visiblePlayers;
	
	List<Bullet> visibleBullets;

	public Long getId() {
		return id;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	public long getScore() {
		return score;
	}

	public String getShipType() {
		return shipType;
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

	public Long getShipHp() {
		return shipHp;
	}

	public void setShipHp(Long shipHp) {
		this.shipHp = shipHp;
	}

	public long getMaxShieldAmount() {
		return maxShieldAmount;
	}

	public void setMaxShieldAmount(long maxShieldAmount) {
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

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public long getRespawnTime() {
		return respawnTime;
	}

	public void setRespawnTime(long respawnTime) {
		this.respawnTime = respawnTime;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
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

	public List<Bullet> getVisibleBullets() {
		return visibleBullets;
	}
	
	public List<PlayerData> getVisiblePlayers() {
		return visiblePlayers;
	}

	public long getShieldAmount() {
		return shieldAmount;
	}

	public void setShieldAmount(long shieldAmount) {
		this.shieldAmount = shieldAmount;
	}

	public void setVisiblePlayers(List<PlayerData> visiblePlayers) {
		this.visiblePlayers = visiblePlayers;
	}

	public void setVisibleBullets(List<Bullet> visibleBullets) {
		this.visibleBullets = visibleBullets;
	}
}
