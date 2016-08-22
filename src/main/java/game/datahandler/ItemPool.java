package game.datahandler;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import game.config.constant.GameConfig;
import game.datatype.PlayerData;
import game.interfaces.ItemPoolList;
import game.interfaces.PlayerPoolMap;
import game.interfaces.SpawnableItem;

public class ItemPool implements ItemPoolList<SpawnableItem> {
    private List<SpawnableItem> itemPool;

    @Autowired
    private ItemHandler itemHandler;

    @Autowired
    private PlayerPoolMap<Long, PlayerData> playerPool;

    public ItemPool() {
        itemPool = new CopyOnWriteArrayList<SpawnableItem>();
    }

    @Override
    public List<SpawnableItem> getAllOnScreen(Long playerId) {
        CopyOnWriteArrayList<SpawnableItem> allItemsOnScreen = new CopyOnWriteArrayList<SpawnableItem>();
        Iterator<SpawnableItem> bit = this.getIterator();

        PlayerData playerData = playerPool.get(playerId);

        while (bit.hasNext()) {
            SpawnableItem item = bit.next();

            if (itemHandler.isItOnScreen(playerData, item)) {
                allItemsOnScreen.add(item);
            }
        }
        return allItemsOnScreen;
    }

    @Override
    public void createNewRandomItem() {
        if (isPlaceItem()) {
            SpawnableItem item = new ItemCreationHandler().createRandomItem();
            this.add(item);
        }
    }

    private boolean isPlaceItem() {
        return (this.poolSize() < GameConfig.MAX_ITEMS_ON_STAGE
                && (new Random().nextInt(GameConfig.ITEM_SPAWNING_RATE) < 16));
    }

    @Override
    public boolean add(SpawnableItem item) {
        return this.itemPool.add(item);
    }

    @Override
    public boolean remove(SpawnableItem item) {
        return this.itemPool.remove(item);
    }

    @Override
    public void clear() {
        this.itemPool.clear();
    }

    @Override
    public int poolSize() {
        return itemPool.size();
    }

    @Override
    public Iterator<SpawnableItem> getIterator() {
        return itemPool.iterator();
    }
}
