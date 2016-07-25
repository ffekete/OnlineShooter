package model;

/** This data will be sent to the client side after a registration attempt. */
public class QualifiedPlayerData {
	private PlayerData playerData;
	private boolean registered;
	
	/* Getters/setters and constructor */
	public PlayerData getPlayerData() {
		return playerData;
	}
	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	
	public QualifiedPlayerData(){
		this.playerData = null;
		this.registered = false;
	}
}