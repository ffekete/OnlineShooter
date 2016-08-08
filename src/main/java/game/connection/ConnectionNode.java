package game.connection;

public class ConnectionNode {
    private final String path;
    
    private final Long id;

    public String getPath() {
        return path;
    }

    public Long getId() {
        return id;
    }

    public ConnectionNode(String path, Long id) {
        super();
        this.path = path + id;
        this.id = id;
    }
}
