package game.datahandler;

import java.awt.geom.Point2D;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.constant.GameConfig;
import game.datatype.PlayerData;
import game.interfaces.ItemPoolList;
import game.interfaces.Spawnable;
import game.interfaces.SpawnableItem;
import game.util.RandomGenerator;

@Component
public class ItemHandler {

    @Autowired
    private ItemPoolList<SpawnableItem> itemPool;

    public boolean isItOnScreen(PlayerData player, Spawnable item) {
        boolean result = false;

        if ((Math.abs(item.getX() - player.getX()) <= player.getScreenHalfWidth())
                && (Math.abs(item.getY() - player.getY()) <= player.getScreenHalfHeight())) {
            result = true;
        }

        return result;
    }

    public void dropCargoToCoordinate(List<SpawnableItem> carriage, Point2D coordinate) {
        for (SpawnableItem item : carriage) {
            Point2D itemDropCoordinate = new Point2D.Double();
            itemDropCoordinate.setLocation(
                    coordinate.getX() + RandomGenerator.getRandomInRange(-1 * (GameConfig.MAX_ITEM_DROP_DISTANCE),
                            GameConfig.MAX_ITEM_DROP_DISTANCE),
                    coordinate.getY() + RandomGenerator.getRandomInRange(-1 * (GameConfig.MAX_ITEM_DROP_DISTANCE),
                            GameConfig.MAX_ITEM_DROP_DISTANCE));
            item.setCoordinate(itemDropCoordinate);
            itemPool.add(item);
        }
    }
}