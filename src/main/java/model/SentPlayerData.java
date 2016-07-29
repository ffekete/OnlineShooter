package model;

import java.util.List;

import interfaces.SpawnableItem;
import interfaces.Weapon;

public class SentPlayerData {
	private Long id;
	private double x;
	private double y;
	
	List<String> scores;
	
	private long score;
	
	private List<SpawnableItem> items;
	
	private Double shipAngle;
	
	private Long connectionId;
	
	private Long shipHp;
	
	private boolean invulnerable;
	
	Weapon weapon;
	
	List<PlayerData> visiblePlayers;
	
	List<BulletData> visibleBullets;

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

	public Long getShipHp() {
		return shipHp;
	}

	public void setShipHp(Long shipHp) {
		this.shipHp = shipHp;
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

	public List<BulletData> getVisibleBullets() {
		return visibleBullets;
	}
	
	public List<PlayerData> getVisiblePlayers() {
		return visiblePlayers;
	}

	public void setVisiblePlayers(List<PlayerData> visiblePlayers) {
		this.visiblePlayers = visiblePlayers;
	}

	public void setVisibleBullets(List<BulletData> visibleBullets) {
		this.visibleBullets = visibleBullets;
	}
}
