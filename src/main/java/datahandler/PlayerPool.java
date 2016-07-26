package datahandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.ConnectionPreferences;
import model.PlayerData;

/** All registered players are stored here. Players are identified by their id. */
@Component
public class PlayerPool {
	private Map<Long, PlayerData> playerPool;

	/** A convenient alis to store a new player. Returns true if registration was successful. */
	public boolean registerPlayer(Long id, String name) {
		return storePlayer(id, name);
	}

	/** Stores a new player to a bean called playerPool. Returns true if the player id was unique and the store was successful. */
	public boolean storePlayer(Long id, String name) {

		if (playerPool.containsKey(id)) {
			return false;
		} else {
			PlayerData newPlayer = new PlayerData(id, name);
			playerPool.put(id, newPlayer);
			return true;
		}
	}

	public void increasePlayerInactivityCounters(){
		for(Long i : playerPool.keySet()){
			PlayerData currentPlayer = playerPool.get(i);
			
			currentPlayer.increaseInactivityCounter();
		}
	}
	
	public void removeInactivePlayers(){
		for(Long i : playerPool.keySet()){
			PlayerData currentPlayer = playerPool.get(i);
			
			if(currentPlayer.getInactivityCounter() >= ConnectionPreferences.PLAYER_INACTIVITY_LIMIT){
				playerPool.remove(i);
			}
		}
	}
	
	public void resetInactivityOfPlayer(Long id){
		PlayerData player = null;

		if (playerPool.containsKey(id)) {
			player = playerPool.get(id);
			player.setInactivityCounter(0);
		}
	}
	
	/** Searches playerPool and tries to find the user with a given id. Returns null, if the player was not found. */
	public PlayerData getPlayerById(Long id) {
		PlayerData player = null;

		if (playerPool.containsKey(id)) {
			player = playerPool.get(id);
		}

		return player;
	}

	/** Creates a new HashMap to store player data. */
	public PlayerPool() {
		this.playerPool = new HashMap<Long, PlayerData>();
	}

	/* Getters/setters */
	public Map<Long, PlayerData> getPool() {
		return playerPool;
	}
}
