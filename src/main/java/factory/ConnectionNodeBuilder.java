package factory;

import game.connection.ConnectionNode;

public class ConnectionNodeBuilder {

    private String providePlayerDataPath = null;
    private Long connectionId = null;
    
    public ConnectionNodeBuilder setProvidePlayerDataPath(String providePlayerDataPath){
        this.providePlayerDataPath = providePlayerDataPath;
        return this;
    }
    
    public ConnectionNodeBuilder setConnectionId(Long connectionId){
        this.connectionId = connectionId;
        return this;
    }
    
    public ConnectionNode build(){
        if(providePlayerDataPath == null){
            throw new NullPointerException("(E) providePlayerDataPath cannot be null!");
        }
        if(connectionId == null){
            throw new NullPointerException("(E) connectionId cannot be null!");
        }
        
        ConnectionNode result = new ConnectionNode(providePlayerDataPath, connectionId); 
        
        providePlayerDataPath = null;
        connectionId = null;
        
        return result;
    }
    
}
