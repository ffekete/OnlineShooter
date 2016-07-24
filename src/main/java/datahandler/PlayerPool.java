package datahandler;

import java.util.Map;

import model.PlayerData;

public class PlayerPool {
	private Map<Long, PlayerData> playerPool;
	
	public Map<Long, PlayerData> getPool(){
		return playerPool;
	}
	
	public PlayerPool(Map<Long, PlayerData> playerPool){
		this.playerPool = playerPool;
	}
	
	public boolean registerPlayer(Long id, String name){
		return storePlayer(id, name);
	}
	
	public boolean storePlayer(Long id, String name){
		
		if(playerPool.containsKey(id)){
			return false;
		}
		else
		{
			PlayerData newPlayer = new PlayerData(id, name);
			playerPool.put(id, newPlayer);
			return true;
		}
	}
	
	public PlayerData getPlayerById(Long id){
		PlayerData player = null;
		
		if(playerPool.containsKey(id)){
			player = playerPool.get(id);
		}
		
		return player;
	}
}
