package model;

public class QualifiedPlayerData {
	private PlayerData playerData;
	private boolean registered;
	
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