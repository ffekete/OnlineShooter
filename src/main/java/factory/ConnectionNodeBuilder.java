package factory;

import org.springframework.stereotype.Component;

import game.connection.ConnectionNode;

@Component
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
        
        return new ConnectionNode(providePlayerDataPath, connectionId);
    }
    
}
