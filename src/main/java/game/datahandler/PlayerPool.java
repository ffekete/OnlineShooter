package game.datahandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.ConnectionPreferences;
import game.connection.ConnectionPool;
import game.datatypes.HighScore;
import game.datatypes.PlayerData;
import game.datatypes.RegistrationData;

@Component
public class PlayerPool {

    @Autowired
    private ConnectionPool connectionPool;

    @Autowired
    private HighScoreTable highScores;
    
    @Autowired
    private ItemHandler itemHandler;
    
    private Map<Long, PlayerData> playerPool;

    public Set<Long> getAllPlayerIds(){
    	return playerPool.keySet();
    }
    
    public Iterator<Long> getKeySetIterator(){
    	return getAllPlayerIds().iterator();
    }
    
    /** This function should be called periodically from the game loop. */
    public void updatePlayerPoolData(){
        increasePlayerInactivityCounters();
        removeInactivePlayersAndStoreHighScore();
    }
    
    public boolean registerPlayer(Long id, RegistrationData data) {
        return storePlayer(id, data);
    }

    public boolean storePlayer(Long newPlayerId, RegistrationData data) {

        if (playerPool.containsKey(newPlayerId)) {
            return false;
        } else {
            PlayerData newPlayerData = new PlayerData(newPlayerId, data.getName(), data.getShipType());
            Long connectionId = connectionPool.registerNewConnection(newPlayerId);

            if (connectionId != null) {
                newPlayerData.setConnectionId(connectionId);
                newPlayerData.setColor(data.getColor());
                newPlayerData.setShipType(data.getShipType());
                playerPool.put(newPlayerId, newPlayerData);
                return true;
            }
            return false;
        }
    }

    private void increasePlayerInactivityCounters() {
        for (long i : playerPool.keySet()) {
            PlayerData currentPlayer = playerPool.get(i);

            currentPlayer.increaseInactivityCounter();
        }
    }

    public void removePlayer(PlayerData player) {

        long playerId = player.getId();
        
        connectionPool.removeConnectionNode(playerId);
        System.out.println("Removing dead player with id " + playerId + ".");
        playerPool.remove(playerId);
    }

    private void removeInactivePlayersAndStoreHighScore() {
        for (long i : playerPool.keySet()) {
            PlayerData currentPlayer = playerPool.get(i);

            if (currentPlayer.getInactivityCounter() >= ConnectionPreferences.PLAYER_INACTIVITY_LIMIT) {
                highScores.addScore(new HighScore(currentPlayer.getScore(), currentPlayer.getName()));
                highScores.KeepTopThreePlayersInHighScoreTable();
                connectionPool.removeConnectionNode(i);
                System.out.println("Removing inactive player with id " + i + ".");
                playerPool.remove(i);
            }
        }
    }

    public void resetInactivityOfPlayer(Long id) {
        PlayerData player = null;

        if (playerPool.containsKey(id)) {
            player = playerPool.get(id);
            player.setInactivityCounter(0);
        }
    }

    public PlayerData getPlayerById(Long id) {
        PlayerData player = null;

        if (playerPool.containsKey(id)) {
            player = playerPool.get(id);
        }

        return player;
    }

    public PlayerPool() {
        this.playerPool = new ConcurrentHashMap<Long, PlayerData>();
    }

    /* Getters/setters */
    public Map<Long, PlayerData> getPool() {
        return playerPool;
    }

    public List<PlayerData> getAllPlayersOnScreen(Long playerId) {
        ArrayList<PlayerData> visiblePlayers = new ArrayList<>();

        PlayerData player = playerPool.get(playerId);

        for (Long id : playerPool.keySet()) {
            PlayerData currentPlayer = playerPool.get(id);
            if (currentPlayer.getId() != playerId) {
                if (itemHandler.isItOnScreen(player, currentPlayer.getSpaceShip())) {
                    visiblePlayers.add(currentPlayer);
                }
            }
        }

        return visiblePlayers;
    }
}
