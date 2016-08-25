package game.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import factory.ConnectionNodeBuilder;
import game.entrypoint.Application;

@ContextConfiguration(classes = Application.class)
public class ConnectionNodeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ConnectionNodeBuilder connectionNodeBuilder;

    @Test
    void testShouldCreateOneConnectionNode() {
        ConnectionNode cn = new ConnectionNode("/TestPath_", 112L);

        Assert.assertEquals(cn.getPath(), "/TestPath_112");
    }

    @Test
    void testShouldCreateOneConnectionNodeWithBuilderClass() {
        ConnectionNode cn = connectionNodeBuilder.setConnectionId(Long.MAX_VALUE)
                .setProvidePlayerDataPath("/TestPath__").build();

        Assert.assertEquals(cn.getPath(), "/TestPath__" + Long.MAX_VALUE);
    }
}
