package factory;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.interfaces.SpawnableItem;

public class CarriageBuilderTest {

    @Test
    public void testShouldFillHalfOfTheCarriage() {
        List<SpawnableItem> carriage = CarriageBuilder.buildHalfCargo(10);
        Assert.assertEquals(carriage.size(), 5);
    }

    @Test
    public void testShouldGiveBackEmptyCarriageForCargoSizeZero() {
        List<SpawnableItem> carriage = CarriageBuilder.buildHalfCargo(0);
        Assert.assertEquals(carriage.size(), 0);
    }

    @Test
    public void testShouldGiveBackEmptyCarriageForCargoSizeOne() {
        List<SpawnableItem> carriage = CarriageBuilder.buildHalfCargo(1);
        Assert.assertEquals(carriage.size(), 0);
    }
}
