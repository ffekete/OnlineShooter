package game.datahandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.ConnectionPreferences;
import game.connection.ConnectionPool;
import game.model.HighScore;
import game.model.PlayerData;
import game.model.RegistrationData;

/**
 * All registered players are stored here. Players are identified by their id.
 */
@Component
public class PlayerPool {

	@Autowired
	ConnectionPool connectionPool;

	@Autowired
	HighScoreTable highScores;
	
	private Map<Long, PlayerData> playerPool;

	public void updatePlayerPoolData(){
		increasePlayerInactivityCounters();
		removeInactivePlayers();
	}
	
	/**
	 * A convenient alis to store a new player. Returns true if registration was
	 * successful.
	 */
	public synchronized boolean registerPlayer(Long id, RegistrationData data) {
		return storePlayer(id, data);
	}

	/**
	 * Stores a new player to a bean called playerPool. Returns true if the
	 * player id was unique and the store was successful.
	 */
	public synchronized boolean storePlayer(Long id, RegistrationData data) {

		if (playerPool.containsKey(id)) {
			return false;
		} else {
			PlayerData newPlayer = new PlayerData(id, data.getName(), data.getShipType());
			Long connectionId = connectionPool.registerNewConnection(id);

			if (connectionId != null) {
				newPlayer.setConnectionId(connectionId);
				newPlayer.setColor(data.getColor());
				newPlayer.setShipType(data.getShipType());
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
				highScores.addScore(new HighScore(currentPlayer.getScore(), currentPlayer.getName()));
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
				if ((Math.abs(currentPlayer.getX() - player.getX()) <= player.getCanvas().getHalfWidth())
						&& (Math.abs(currentPlayer.getY() - player.getY()) <= player.getCanvas().getHalfHeight())) {
					visiblePlayers.add(currentPlayer);
				}
			}
		}

		return visiblePlayers;
	}
}
