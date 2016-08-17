package datahandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.ConnectionPreferences;
import game.connection.ConnectionPool;
import game.entrypoint.Application;

@ContextConfiguration(classes={Application.class})
public class ConnectionPoolTest extends AbstractTestNGSpringContextTests{

    @Autowired
    ConnectionPool connectionPool;
    
    @BeforeMethod
    public void initPool(){
        connectionPool.clear();
    }
    
    @Test
    public void testShouldFindOneAvailableConnectionNode(){
        long id = connectionPool.findAvailableConnectionNodeId();
        
        Assert.assertEquals(id, 0L);
    }

    @Test
    public void testShouldThrowNoSuchElementException(){
        connectionPool.registerNewConnection(200L);
        
        Assert.assertEquals(connectionPool.getPoolSize(), 1);
        
        connectionPool.removeConnectionNode(null);
     }
    
    @Test
    public void testShouldReturnOneConnection(){
        connectionPool.registerNewConnection(200L);
        
        Assert.assertEquals(connectionPool.getPoolSize(), 1);
        
        connectionPool.removeConnectionNode(200L);
        
        Assert.assertEquals(connectionPool.getPoolSize(), 0);
    }
    
    @Test
    public void testShouldReturnNull_poolIsFull(){
        
        for(int i = 0; i < ConnectionPreferences.SERVER_MAX_CAPACITY; i++){
            connectionPool.registerNewConnection(200L + i);
        }
        
        Long id = connectionPool.findAvailableConnectionNodeId();
        
        Assert.assertEquals(id, null);
    }
    
    @Test
    public void testShouldFindOneAvailableConnectionNode_WithValueOf_1(){
        
        connectionPool.registerNewConnection(200L);
        long id = connectionPool.findAvailableConnectionNodeId();
      
        Assert.assertEquals(id, 1L);
    }
}
