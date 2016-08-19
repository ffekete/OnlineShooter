package game.datahandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.constant.ConnectionPreferences;
import game.connection.ConnectionPool;
import game.datatype.HighScore;
import game.datatype.PlayerData;
import game.datatype.RegistrationData;
import game.interfaces.PlayerPoolMap;
import game.transformer.RegistrationDataToPlayerDataTransformer;

@Component
public class PlayerPool implements PlayerPoolMap<Long, PlayerData> {

    @Autowired
    private ConnectionPool connectionPool;

    @Autowired
    private HighScoreTable highScores;

    @Autowired
    private ItemHandler itemHandler;

    @Autowired
    RegistrationDataToPlayerDataTransformer registrationDataToPlayerDataTransformer;

    private Map<Long, PlayerData> playerPool;

    public PlayerPool() {
        this.playerPool = new ConcurrentHashMap<Long, PlayerData>();
    }

    @Override
    public Set<Long> getAll() {
        return playerPool.keySet();
    }

    @Override
    public Iterator<Long> getKeySetIterator() {
        return getAll().iterator();
    }

    /** This function should be called periodically from the game loop. */
    @Override
    public void updatePlayerPoolData() {
        increasePlayerInactivityCounters();
        removeInactivePlayersAndStoreHighScore();
    }

    @Override
    public boolean registerPlayer(Long id, RegistrationData data) {
        return storePlayer(id, data);
    }

    public boolean storePlayer(Long newPlayerId, RegistrationData data) {
        if (playerPool.containsKey(newPlayerId)) {
            return false;
        } else {
            PlayerData newPlayerData = registrationDataToPlayerDataTransformer.transform(data, newPlayerId);
            Long connectionId = connectionPool.registerNewConnection(newPlayerId);

            if (connectionId != null) {
                newPlayerData.setConnectionId(connectionId);
                newPlayerData.setColor(data.getColor());
                newPlayerData.setShipType(data.getShipType());
                this.put(newPlayerId, newPlayerData);
                return true;
            }
            return false;
        }
    }

    private void increasePlayerInactivityCounters() {
        for (long i : this.getAll()) {
            PlayerData currentPlayer = this.get(i);

            currentPlayer.increaseInactivityCounter();
        }
    }

    public void removePlayer(PlayerData player) {
        long playerId = player.getId();

        connectionPool.removeConnectionNode(playerId);
        System.out.println("Removing dead player with id " + playerId + ".");
        this.remove(playerId);
    }

    private void removeInactivePlayersAndStoreHighScore() {
        for (long i : this.getAll()) {
            PlayerData currentPlayer = this.get(i);

            if (currentPlayer.getInactivityCounter() >= ConnectionPreferences.PLAYER_INACTIVITY_LIMIT) {
                highScores.addScore(new HighScore(currentPlayer.getScore(), currentPlayer.getName()));
                highScores.KeepTopThreePlayersInHighScoreTable();
                connectionPool.removeConnectionNode(i);
                System.out.println("Removing inactive player with id " + i + ".");
                this.remove(i);
            }
        }
    }

    @Override
    public void resetInactivityOfPlayer(Long id) {
        if (playerPool.containsKey(id)) {
            PlayerData player = this.get(id);
            player.setInactivityCounter(0);
        }
    }

    /* Getters/setters */
    @Override
    public List<PlayerData> getAllOnScreen(Long playerId) {
        ArrayList<PlayerData> visiblePlayers = new ArrayList<>();

        PlayerData player = this.get(playerId);

        for (Long id : this.getAll()) {
            PlayerData currentPlayer = this.get(id);
            if (currentPlayer.getId() != playerId) {
                if (itemHandler.isItOnScreen(player, currentPlayer.getSpaceShip())) {
                    visiblePlayers.add(currentPlayer);
                }
            }
        }

        return visiblePlayers;
    }

    @Override
    public void put(Long id, PlayerData player) {
        playerPool.put(id, player);
    }

    @Override
    public void remove(Long playerId) {
        playerPool.remove(playerId);
    }

    @Override
    public PlayerData get(Long playerId) {
        return playerPool.get(playerId);
    }
}
