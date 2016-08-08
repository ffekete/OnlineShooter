package game.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import game.config.BrokerPaths;
import game.config.ConnectionPreferences;

/** This component stores all connection nodes that are paired to the active players.
 *  Connection nodes are stored in a map and each player ids have exactly one connection node. 
 *  */
@Component
public class ConnectionPool {
	/** A new connection will be paired to each player id */
	private Map<Long, ConnectionNode> connectionPool;
	
	/** Removes a connection node that belongs to the given player id. */
	public void removeConnectionNode(Long playerId){
		System.out.println("Connection node removed with id " + connectionPool.get(playerId).getId());
		connectionPool.remove(playerId);
	}
	
	/** function tries to register a new connection. If the pool is full, it returns null, if not, it stores a new connection node with id and path in the pool and returns with the reference to the connectionId. */
	public Long registerNewConnection(Long playerId){
		Long connectionId = findAvailableConnectionNodeId();
		
		/* If there is a free connection slot, register a new connection and store it in the pool */
		if(connectionId != null){
			ConnectionNode connectionNode = new ConnectionNode(BrokerPaths.PROVIDE_PLAYER_DATA, connectionId);
			connectionPool.put(playerId, connectionNode);
			System.out.println("Connection registered for player id " + playerId + " to path " + connectionNode.getPath());
			return connectionId;
		}
		else
		{
			/* There is no free connection slot... */
			return null;
		}
	}
	
	/** All connections are identified by an id. A path will be composed of 'providePlayerData_node' + id.
	 *  Id is ranged from 1 to SERVER_MAX_CAPACITY.
	 *  This function finds an available id for a new connection. If server is empty it returns null.
	 *  */
	public Long findAvailableConnectionNodeId(){
		if(this.isFull()){
			return null;
		}
		else
		{
			/* Get all available connections by removing all used from the list */
			ArrayList<Long> availableConnections = new ArrayList<Long>();
			for(long i = 0; i < ConnectionPreferences.SERVER_MAX_CAPACITY; i++){
				availableConnections.add(i);
			}
			/* Remove used connection ids */
			for(Long id: connectionPool.keySet()){
				ConnectionNode node = connectionPool.get(id);
				availableConnections.remove(node.getId());
			}
			
			return availableConnections.get(0); // retrieve first element
		}
	}
	
	public boolean isFull(){
		if(connectionPool.size() >= ConnectionPreferences.SERVER_MAX_CAPACITY){
			return true;
		}
		return false;
	}
	
	public ConnectionPool(){
		connectionPool = new HashMap<Long, ConnectionNode>();
	}
}
