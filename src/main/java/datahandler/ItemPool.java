package datahandler;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import Items.HealthPack;
import Items.IncreaseDamage;
import Items.IncreaseMAneuverability;
import Items.IncreaseRateOfFire;
import Items.IncreaseSpeed;
import builder.WeaponFactory;
import config.CanvasConstants;
import config.GameConfig;
import config.WeaponId;
import interfaces.Spawnable;
import interfaces.SpawnableItem;
import model.BulletData;
import model.PlayerData;
import weapons.LaserCannon;

@Component
public class ItemPool {
	List<SpawnableItem> itemPool;

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
			if ((Math.abs(item.getX() - playerData.getX()) <= CanvasConstants.CANVAS_HALF_WIDTH)
					&& (Math.abs(item.getY() - playerData.getY()) <= CanvasConstants.CANVAS_HALF_HEIGHT)) {
				allItemsOnScreen.add(item);
			}
		}
		return allItemsOnScreen;
	}

	public void removeItem(SpawnableItem item) {
		itemPool.remove(item);
	}

	public void createNewRandomItem() {
		Random random = new Random();
		SpawnableItem newItem = null;

		if (itemPool.size() < GameConfig.MAX_ITEMS_ON_STAGE) {
			int i = random.nextInt(800);

			switch (i) {
			case 1:
				newItem = (SpawnableItem) new WeaponFactory().createWeapon(WeaponId.GATLING_GUN);
				break;
			case 2:
				newItem = (SpawnableItem) new IncreaseDamage();
				break;
			case 3:
				newItem = (SpawnableItem) new HealthPack();
				break;
			case 4:
				newItem = (SpawnableItem) new IncreaseRateOfFire();
				break;
			case 5:
				newItem = (SpawnableItem) new WeaponFactory().createWeapon(WeaponId.LASER_CANNON);
				break;
			case 6:
				newItem = (SpawnableItem) new IncreaseMAneuverability();
				break;
			case 7:
				newItem = (SpawnableItem) new IncreaseSpeed();
				break;
			default:
				break;
			}
			if (newItem != null) {
				addItem(newItem);
			}
		}
	}
}
