package connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import config.BrokerPaths;
import config.ConnectionPreferences;

@Component
public class ConnectionPool {
	
	private static Long id = 0L;
	
	/** A new connection will be paired to each player id */
	private Map<Long, ConnectionNode> connectionPool;
	
	public void removeConnectionNode(Long playerId){
		System.out.println("Connection node removed with id " + connectionPool.get(playerId).getId());
		connectionPool.remove(playerId);
	}
	
	/** function tries to register a new connection. If the pool is full, it returns false, if not, it stores a new connection node with id and path in the pool. */
	public Long registerNewConnection(Long playerId){
		Long connectionId = findAvailableConnectionNodeId();
		
		if(connectionId != null){
			ConnectionNode connectionNode = new ConnectionNode(BrokerPaths.PROVIDE_PLAYER_DATA, connectionId);
			connectionPool.put(playerId, connectionNode);
			System.out.println("Connection registered for player id " + playerId + " to path " + connectionNode.getPath());
			return connectionId;
		}
		else
		{
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
			id++;
			/* Get all available connections by removing all used from the list */
			ArrayList<Long> availableConnections = new ArrayList<Long>();
			for(long i = 0; i < ConnectionPreferences.SERVER_MAX_CAPACITY; i++){
				availableConnections.add(i);
			}
			
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
