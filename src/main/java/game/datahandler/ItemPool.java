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
import game.interfaces.SpawnableItem;

@Component
public class ItemPool {
    private List<SpawnableItem> itemPool;

    @Autowired
    private ItemHandler itemHandler;

    public ItemPool() {
        itemPool = new CopyOnWriteArrayList<SpawnableItem>();
    }

    public void addItem(SpawnableItem item) {
        itemPool.add(item);
    }

    public List<SpawnableItem> getAllItemsOnScreen(PlayerData playerData) {
        CopyOnWriteArrayList<SpawnableItem> allItemsOnScreen = new CopyOnWriteArrayList<SpawnableItem>();
        Iterator<SpawnableItem> bit = itemPool.iterator();

        while (bit.hasNext()) {
            SpawnableItem item = bit.next();

            if (itemHandler.isItOnScreen(playerData, item)) {
                allItemsOnScreen.add(item);
            }
        }
        return allItemsOnScreen;
    }

    public void removeItem(SpawnableItem item) {
        itemPool.remove(item);
    }

    public void createNewRandomItem() {
        if (itemPool.size() < GameConfig.MAX_ITEMS_ON_STAGE) {
            int itemSpawned = new Random().nextInt(GameConfig.ITEM_SPAWNING_RATE);

            switch (itemSpawned) {
            case SpawnableItemTypeConstants.GATLING_GUN:
                addItem((SpawnableItem) WeaponFactory.createWeapon(WeaponId.GATLING_GUN));
                break;
            case SpawnableItemTypeConstants.INCREASE_DAMAGE:
                addItem((SpawnableItem) new IncreaseDamage());
                break;
            case SpawnableItemTypeConstants.HEALTH_PACK:
                addItem((SpawnableItem) new HealthPack());
                break;
            case SpawnableItemTypeConstants.INCRASE_RATE_OF_FIRE:
                addItem((SpawnableItem) new IncreaseRateOfFire());
                break;
            case SpawnableItemTypeConstants.LASER_CANNON:
                addItem((SpawnableItem) WeaponFactory.createWeapon(WeaponId.LASER_CANNON));
                break;
            case SpawnableItemTypeConstants.INCREASE_MANEUVERABILITY:
                addItem((SpawnableItem) new IncreaseMAneuverability());
                break;
            case SpawnableItemTypeConstants.INCREASE_SPEED:
                addItem((SpawnableItem) new IncreaseSpeed());
                break;
            case SpawnableItemTypeConstants.INCREASE_SCORE_1:
            case SpawnableItemTypeConstants.INCREASE_SCORE_2:
            case SpawnableItemTypeConstants.INCREASE_SCORE_3:
            case SpawnableItemTypeConstants.INCREASE_SCORE_4:
                addItem((SpawnableItem) new IncreaseScore());
                break;
            case SpawnableItemTypeConstants.ATOM_SHIELD:
                addItem((SpawnableItem) ShieldFactory.createShield(ShieldId.ATOM_SHIELD));
                break;
            case SpawnableItemTypeConstants.PLASMA_SHIELD:
                addItem((SpawnableItem) ShieldFactory.createShield(ShieldId.PLASMA_SHIELD));
                break;
            case SpawnableItemTypeConstants.DOUBLE_GATLING_GUN:
                addItem((SpawnableItem) WeaponFactory.createWeapon(WeaponId.DOUBLE_GATLING));
                break;
            case SpawnableItemTypeConstants.SHOTGUN:
                addItem((SpawnableItem) WeaponFactory.createWeapon(WeaponId.SHOTGUN));
                break;
            }
        }
    }
}
