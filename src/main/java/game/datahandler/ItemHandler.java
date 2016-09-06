package game.datahandler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import game.datatype.PlayerData;
import game.interfaces.ItemPoolList;
import game.interfaces.Ship;
import game.interfaces.Spawnable;
import game.interfaces.SpawnableItem;
import game.service.CoordinateHandler;

public class ItemHandler {

    @Autowired
    private ItemPoolList<SpawnableItem> itemPool;

    @Autowired
    private CoordinateHandler coordinateHandler;

    public boolean isItOnScreen(PlayerData player, Spawnable item) {
        boolean result = false;

        if ((Math.abs(item.getX() - player.getX()) <= player.getScreenHalfWidth())
                && (Math.abs(item.getY() - player.getY()) <= player.getScreenHalfHeight())) {
            result = true;
        }

        return result;
    }

    public void dropCargoToCoordinate(Ship ship) {
        List<SpawnableItem> carriage = ship.getCarriage();
        for (SpawnableItem item : carriage) {
            item.setCoordinate(coordinateHandler.calculateItemCoordinates(item, ship.getSpeed(), ship.getCoordinate()));
            itemPool.add(item);
        }
        ship.resetCarriage();
    }
}