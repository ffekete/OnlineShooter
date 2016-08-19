package connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import factory.ConnectionNodeBuilder;
import game.connection.ConnectionNode;
import game.entrypoint.Application;

@ContextConfiguration(classes=Application.class)
public class ConnectionNodeTest extends AbstractTestNGSpringContextTests{

    @Autowired
    ConnectionNodeBuilder connectionNodeBuilder;
    
    @Test
    void testShouldCreateOneConnectionNode() {
        ConnectionNode cn = new ConnectionNode("/TestPath_", 112L);
        
        Assert.assertEquals(cn.getPath(), "/TestPath_112");
    }
    
    @Test
    void testShouldCreateOneConnectionNodeWithBuilderClass(){
        ConnectionNode cn = connectionNodeBuilder.setConnectionId(Long.MAX_VALUE).setProvidePlayerDataPath("/TestPath__").build();
        
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
        connectionNodeBuilder.setConnectionId(id).setProvidePlayerDataPath(path).build();
    }
    
    @Test(expectedExceptions = {NullPointerException.class})
    void testShouldThrowNullPointerException_NoSetterWasInvoked(){
        connectionNodeBuilder.build();
    }
    
    @Test(expectedExceptions = {NullPointerException.class})
    void testShouldThrowNullPointerException_ShouldNotKeepPreviousValues(){
                
        ConnectionNode cn = connectionNodeBuilder.setConnectionId(12L).setProvidePlayerDataPath("/Path_").build();
        cn = connectionNodeBuilder.setProvidePlayerDataPath("/NewPath").build();
        
        System.out.println(cn.getPath());
    }
}
