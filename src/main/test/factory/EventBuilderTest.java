package factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import game.entrypoint.Application;

@ContextConfiguration(classes = { Application.class })
public class EventBuilderTest extends AbstractTestNGSpringContextTests {

    @Autowired
    EventBuilder eventBuilder;

    @Test(expectedExceptions = NullPointerException.class)
    void testShouldThrowNullPointerExceptionForNoParameter() {
        eventBuilder.build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    void testShouldThrowNullPointerExceptionForNoEventCommandParameter() {
        eventBuilder.setEventXCoordinate(1.0d).setEventYCoordinate(1.0d).build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    void testShouldThrowNullPointerExceptionForNoXParameter() {
        eventBuilder.setEventYCoordinate(2.0d).setEventCommand("command").build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    void testShouldThrowNullPointerExceptionForNoYParameter() {
        eventBuilder.setEventXCoordinate(1.0d).setEventCommand("command").build();
    }
}
