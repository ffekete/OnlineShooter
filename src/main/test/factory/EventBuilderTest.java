package factory;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.datatypes.Event;
import game.interfaces.Bullet;

import static org.mockito.Mockito.*;

import java.awt.geom.Point2D;

import org.mockito.Mock;

public class EventBuilderTest {
    
    @Test(
            expectedExceptions=NullPointerException.class, 
            expectedExceptionsMessageRegExp = ".*command.*")
    public void testShouldThrowCoordinateException() {
        EventBuilder builder = new EventBuilder();
        builder.setEventCommand(null).build();
    }
    @Test(
            expectedExceptions=NullPointerException.class, 
            expectedExceptionsMessageRegExp = ".*x coordinate.*"
    )
    public void testShouldThrowAngleException() {
        EventBuilder builder = new EventBuilder();
        builder.setEventCommand("asdf").build();
    }
    @Test(
            expectedExceptions=RuntimeException.class, 
            expectedExceptionsMessageRegExp = ".*y coordinate.*"
            )
    public void testShouldThrowPlayerIdException() {
        EventBuilder builder = new EventBuilder();
        builder.setEventCommand("asdf")
        .setEventXCoordinate(1.0)
        .build();
    }
    @Test
    public void testShouldBuildEvent() {
        EventBuilder builder = new EventBuilder();
        Event event = builder.setEventCommand("asdf")
        .setEventXCoordinate(1.0)
        .setEventYCoordinate(1.0)
        .setEventAdditionalText("asdf")
        .build();
        
        Assert.assertTrue(event instanceof Event);
    }
}
