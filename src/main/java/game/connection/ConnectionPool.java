package game.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import factory.ConnectionNodeBuilder;
import game.config.constant.BrokerPaths;
import game.config.constant.ConnectionPreferences;

public class ConnectionPool {

    @Autowired
    private ConnectionNodeBuilder connectionNodeBuilder;

    private Map<Long, ConnectionNode> connectionPool;

    public void removeConnectionNode(Long playerId) {
        System.out.println("Connection node removed with id " + playerId);
        connectionPool.remove(playerId);
    }

    public void clear(){
        connectionPool.clear();
    }
    
    public int getPoolSize(){
        return connectionPool.size();
    }
    
    public Long registerNewConnection(Long playerId) {
        Long connectionId = findAvailableConnectionNodeId();
        /*
         * If there is a free connection slot, register a new connection and
         * store it in the pool
         */
        if (connectionId != null) {
            ConnectionNode connectionNode = connectionNodeBuilder.setConnectionId(connectionId)
                    .setProvidePlayerDataPath(BrokerPaths.PROVIDE_PLAYER_DATA).build();

            connectionPool.put(playerId, connectionNode);
            System.out.println(
                    "Connection registered for player id " + playerId + " to path " + connectionNode.getPath());
            return connectionId;
        } else {
            /* There is no free connection slot... */
            return null;
        }
    }

    public Long findAvailableConnectionNodeId() {
        if (this.isConnectionPoolFull()) {
            return null;
        } else {
            /*
             * Get all available connections by removing all used from the list
             */
            ArrayList<Long> availableConnections = new ArrayList<Long>();
            for (long i = 0; i < ConnectionPreferences.SERVER_MAX_CAPACITY; i++) {
                availableConnections.add(i);
            }
            /* Remove used connection ids */
            for (Long id : connectionPool.keySet()) {
                ConnectionNode node = connectionPool.get(id);
                availableConnections.remove(node.getId());
            }

            return availableConnections.get(0); // retrieve first element
        }
    }

    public boolean isConnectionPoolFull() {
        if (connectionPool.size() >= ConnectionPreferences.SERVER_MAX_CAPACITY) {
            return true;
        }
        return false;
    }

    public ConnectionPool() {
        connectionPool = new HashMap<Long, ConnectionNode>();
    }
}
