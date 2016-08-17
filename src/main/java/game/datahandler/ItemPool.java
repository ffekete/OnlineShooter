package game.datahandler;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import factory.ShieldFactory;
import factory.WeaponFactory;
import game.config.GameConfig;
import game.config.ShieldId;
import game.config.SpawnableItemTypeConstants;
import game.config.WeaponId;
import game.datatypes.PlayerData;
import game.datatypes.Items.HealthPack;
import game.datatypes.Items.IncreaseDamage;
import game.datatypes.Items.IncreaseMAneuverability;
import game.datatypes.Items.IncreaseRateOfFire;
import game.datatypes.Items.IncreaseScore;
import game.datatypes.Items.IncreaseSpeed;
import game.interfaces.ItemPoolList;
import game.interfaces.PlayerPoolMap;
import game.interfaces.SpawnableItem;

@Component
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
            this.create(new Random().nextInt(15));
        }
    }

    private boolean isPlaceItem() {
        return (this.poolSize() < GameConfig.MAX_ITEMS_ON_STAGE
                && (new Random().nextInt(GameConfig.ITEM_SPAWNING_RATE) < 16));
    }

    private void create(int id) {
        switch (id) {
        case SpawnableItemTypeConstants.GATLING_GUN:
            this.add((SpawnableItem) WeaponFactory.createWeapon(WeaponId.GATLING_GUN));
            break;
        case SpawnableItemTypeConstants.INCREASE_DAMAGE:
            this.add((SpawnableItem) new IncreaseDamage());
            break;
        case SpawnableItemTypeConstants.HEALTH_PACK:
            this.add((SpawnableItem) new HealthPack());
            break;
        case SpawnableItemTypeConstants.INCRASE_RATE_OF_FIRE:
            this.add((SpawnableItem) new IncreaseRateOfFire());
            break;
        case SpawnableItemTypeConstants.LASER_CANNON:
            this.add((SpawnableItem) WeaponFactory.createWeapon(WeaponId.LASER_CANNON));
            break;
        case SpawnableItemTypeConstants.INCREASE_MANEUVERABILITY:
            this.add((SpawnableItem) new IncreaseMAneuverability());
            break;
        case SpawnableItemTypeConstants.INCREASE_SPEED:
            this.add((SpawnableItem) new IncreaseSpeed());
            break;
        case SpawnableItemTypeConstants.INCREASE_SCORE_1:
        case SpawnableItemTypeConstants.INCREASE_SCORE_2:
        case SpawnableItemTypeConstants.INCREASE_SCORE_3:
        case SpawnableItemTypeConstants.INCREASE_SCORE_4:
            this.add((SpawnableItem) new IncreaseScore());
            break;
        case SpawnableItemTypeConstants.ATOM_SHIELD:
            this.add((SpawnableItem) ShieldFactory.createShield(ShieldId.ATOM_SHIELD));
            break;
        case SpawnableItemTypeConstants.PLASMA_SHIELD:
            this.add((SpawnableItem) ShieldFactory.createShield(ShieldId.PLASMA_SHIELD));
            break;
        case SpawnableItemTypeConstants.DOUBLE_GATLING_GUN:
            this.add((SpawnableItem) WeaponFactory.createWeapon(WeaponId.DOUBLE_GATLING));
            break;
        case SpawnableItemTypeConstants.SHOTGUN:
            this.add((SpawnableItem) WeaponFactory.createWeapon(WeaponId.SHOTGUN));
            break;
        }
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
