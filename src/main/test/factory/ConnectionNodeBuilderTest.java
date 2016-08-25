package factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import game.connection.ConnectionNode;
import game.entrypoint.Application;

@ContextConfiguration(classes = { Application.class })
public class ConnectionNodeBuilderTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ConnectionNodeBuilder connectionNodeBuilder;

    @Test(dataProvider = "invalidInputForConnectionNodeBuilder", expectedExceptions = NullPointerException.class)
    void testShouldThrowNullPointerException(String path, Long id) {
        connectionNodeBuilder.setConnectionId(id).setProvidePlayerDataPath(path).build();
    }

    @DataProvider(name = "invalidInputForConnectionNodeBuilder")
    Object[][] invalidInputForConnectionNodeBuilder() {
        return new Object[][] { { null, 12L }, { "TestPath_", null }, { null, null } };
    }

    @Test(expectedExceptions = NullPointerException.class)
    void testShouldThrowNullPointerException_ShouldNotKeepPreviousValues() {

        ConnectionNode cn = connectionNodeBuilder.setConnectionId(12L).setProvidePlayerDataPath("/Path_").build();
        cn = connectionNodeBuilder.setProvidePlayerDataPath("/NewPath").build();

        System.out.println(cn.getPath());
    }
}