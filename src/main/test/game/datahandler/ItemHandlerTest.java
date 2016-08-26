package game.datahandler;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import game.config.constant.GameConfig;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;
import game.entrypoint.Application;
import game.interfaces.SpawnableItem;

@ContextConfiguration(classes = { Application.class })
public class ItemHandlerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ItemPool itemPool;

    @Autowired
    ItemHandler itemHandler;

    PlayerData player;

    public List<SpawnableItem> carriage = new ArrayList<SpawnableItem>();

    @BeforeMethod
    public void initPool() {
        AIDao aiDao = new AIDao();
        aiDao.setIsAi(true);
        aiDao.setIsAsteroid(false);
        player = new PlayerData(1L, "Test", ShipConfig.SHIP_TYPE_CARGOSHIP, aiDao);
        itemPool.clear();
        carriage.clear();
        carriage.add(new ItemCreationHandler().createRandomItem());
        player.getSpaceShip().setCarriage(carriage);

    }

    @Test
    public void shouldDropOneSpawnableitem() {
        itemHandler.dropCargoToCoordinate(player.getSpaceShip(), new Point2D.Double(0, 0));

        Assert.assertEquals(itemPool.poolSize(), 1);
    }

    @Test(dataProvider = "coordinatesData")
    public void droppedItemCoordinateShouldBetweenRange(double x, double y) {
        itemHandler.dropCargoToCoordinate(player.getSpaceShip(), new Point2D.Double(x, y));

        Iterator<SpawnableItem> bit = itemPool.getIterator();
        while (bit.hasNext()) {
            SpawnableItem droppedItem = bit.next();

            Assert.assertTrue((droppedItem.getX() >= (-1 * GameConfig.MAX_ITEM_DROP_DISTANCE) + x)
                    && (droppedItem.getX() <= GameConfig.MAX_ITEM_DROP_DISTANCE + x)
                    && (droppedItem.getY() >= (-1 * GameConfig.MAX_ITEM_DROP_DISTANCE) + y)
                    && (droppedItem.getY() <= GameConfig.MAX_ITEM_DROP_DISTANCE + y));
        }
    }

    @DataProvider(name = "coordinatesData")
    public Object[][] getDozensData() {
        return new Object[][] { { 1, 1 }, { 10, 10 }, { 15.6, 12.3 }, { 89.91, 87.2 }, { -1, -1 }, { -10, -10 },
                { -100, -100 }, { -1000, -1000 } };
    }
}