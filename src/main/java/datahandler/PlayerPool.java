package datahandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import model.PlayerData;


@Component
public class PlayerPool {
	private Map<Long, PlayerData> playerPool;
	
	public Map<Long, PlayerData> getPool(){
		return playerPool;
	}
	
	public PlayerPool(){
		playerPool = new HashMap<Long, PlayerData>();
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
