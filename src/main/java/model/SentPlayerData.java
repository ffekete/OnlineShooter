package model;

import java.util.List;

public class SentPlayerData {
	private Long id;
	private double x;
	private double y;
	
	private Double shipAngle;
	
	private Long connectionId;
	
	List<PlayerData> visiblePlayers;
	
	List<BulletData> visibleBullets;

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
