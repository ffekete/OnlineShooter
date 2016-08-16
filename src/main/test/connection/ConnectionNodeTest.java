package connection;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import factory.ConnectionNodeBuilder;
import game.connection.ConnectionNode;

public class ConnectionNodeTest{

    
    @Test
    void testShouldCreateOneConnectionNode(){
        ConnectionNode cn = new ConnectionNode("/TestPath_", 112L);
        
        Assert.assertEquals(cn.getPath(), "/TestPath_112");
    }
    
    @Test
    void testShouldCreateOneConnectionNodeWithBuilderClass(){
        ConnectionNode cn = new ConnectionNodeBuilder().setConnectionId(Long.MAX_VALUE).setProvidePlayerDataPath("/TestPath__").build();
        
        Assert.assertEquals(cn.getPath(), "/TestPath__"+Long.MAX_VALUE);
    }
    
    @DataProvider(name="invalidInputForConnectionNodeBuilder")
    Object[][] invalidInputForConnectionNodeBuilder(){
        return new Object[][]{
            {null, 12L},
            {"TestPath_", null},
            {null, null}
        };
    }
    
    @Test(dataProvider="invalidInputForConnectionNodeBuilder",
            expectedExceptions = {NullPointerException.class})
    void testShouldThrowNullPointerException(String path, Long id){
        new ConnectionNodeBuilder().setConnectionId(id).setProvidePlayerDataPath(path).build();
    }
    
    @Test(expectedExceptions = {NullPointerException.class})
    void testShouldThrowNullPointerException_NoSetterWasInvoked(){
        new ConnectionNodeBuilder().build();
    }
}
