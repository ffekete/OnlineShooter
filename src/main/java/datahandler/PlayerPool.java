package datahandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import config.CanvasConstants;
import config.ConnectionPreferences;
import connection.ConnectionPool;
import model.PlayerData;

/**
 * All registered players are stored here. Players are identified by their id.
 */
@Component
public class PlayerPool {

	@Autowired
	ConnectionPool connectionPool;

	private Map<Long, PlayerData> playerPool;

	public void updatePlayerPoolData(){
		increasePlayerInactivityCounters();
		removeInactivePlayers();
	}
	
	/**
	 * A convenient alis to store a new player. Returns true if registration was
	 * successful.
	 */
	public synchronized boolean registerPlayer(Long id, String name) {
		return storePlayer(id, name);
	}

	/**
	 * Stores a new player to a bean called playerPool. Returns true if the
	 * player id was unique and the store was successful.
	 */
	public synchronized boolean storePlayer(Long id, String name) {

		if (playerPool.containsKey(id)) {
			return false;
		} else {
			PlayerData newPlayer = new PlayerData(id, name);
			Long connectionId = connectionPool.registerNewConnection(id);

			if (connectionId != null) {
				newPlayer.setConnectionId(connectionId);
				playerPool.put(id, newPlayer);
				return true;
			}
			return false;
		}
	}

	private synchronized void increasePlayerInactivityCounters() {
		for (Long i : playerPool.keySet()) {
			PlayerData currentPlayer = playerPool.get(i);

			currentPlayer.increaseInactivityCounter();
		}
	}

	public synchronized void removePlayer(PlayerData player) {

		Long playerId = player.getId();
		
		connectionPool.removeConnectionNode(playerId);
		System.out.println("Removing dead player with id " + playerId + ".");
		playerPool.remove(playerId);
	}

	private synchronized void removeInactivePlayers() {
		for (Long i : playerPool.keySet()) {
			PlayerData currentPlayer = playerPool.get(i);

			if (currentPlayer.getInactivityCounter() >= ConnectionPreferences.PLAYER_INACTIVITY_LIMIT) {
				connectionPool.removeConnectionNode(i);
				System.out.println("Removing inactive player with id " + i + ".");
				playerPool.remove(i);
			}
		}
	}

	public synchronized void resetInactivityOfPlayer(Long id) {
		PlayerData player = null;

		if (playerPool.containsKey(id)) {
			player = playerPool.get(id);
			player.setInactivityCounter(0);
		}
	}

	/**
	 * Searches playerPool and tries to find the user with a given id. Returns
	 * null, if the player was not found.
	 */
	public synchronized PlayerData getPlayerById(Long id) {
		PlayerData player = null;

		if (playerPool.containsKey(id)) {
			player = playerPool.get(id);
		}

		return player;
	}

	/** Creates a new HashMap to store player data. */
	public PlayerPool() {
		this.playerPool = new ConcurrentHashMap<Long, PlayerData>();
	}

	/* Getters/setters */
	public Map<Long, PlayerData> getPool() {
		return playerPool;
	}

	public synchronized List<PlayerData> getAllPlayersOnScreen(Long playerId) {
		ArrayList<PlayerData> visiblePlayers = new ArrayList<>();

		PlayerData player = playerPool.get(playerId);

		for (Long id : playerPool.keySet()) {
			PlayerData currentPlayer = playerPool.get(id);
			if (currentPlayer.getId() != playerId) {
				if ((Math.abs(currentPlayer.getX() - player.getX()) <= CanvasConstants.CANVAS_HALF_WIDTH)
						&& (Math.abs(currentPlayer.getY() - player.getY()) <= CanvasConstants.CANVAS_HALF_HEIGHT)) {
					visiblePlayers.add(currentPlayer);
				}
			}
		}

		return visiblePlayers;
	}
}
