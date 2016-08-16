package datahandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import game.connection.ConnectionNode;
import game.connection.ConnectionPool;
import game.entrypoint.Application;

@ContextConfiguration(classes={Application.class})
public class ConnectionPoolTest extends AbstractTestNGSpringContextTests{

    @Autowired
    ConnectionPool connectionPool;
    
    @Test
    public void testShouldFindOneAvailableConnectionNode(){
        long id = connectionPool.findAvailableConnectionNodeId();
        
        Assert.assertEquals(id, 0L);
    }
    
    @Test
    public void testShouldFindOneAvailableConnectionNode_WithValueOf_1(){
        
        connectionPool.registerNewConnection(200L);
        long id = connectionPool.findAvailableConnectionNodeId();
        
        Map<Long, ConnectionNode> connectionPool = new HashMap<>();
        System.out.println(connectionPool.get(200l));
        
        Assert.assertEquals(id, 1L);
    }
}
