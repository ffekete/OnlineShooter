package datahandler;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import builder.WeaponFactory;
import config.CanvasConstants;
import config.WeaponId;
import interfaces.Spawnable;
import interfaces.SpawnableItem;
import model.BulletData;
import model.PlayerData;

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

	public void createNewRandomItem() {
		Random random = new Random();
		SpawnableItem newItem = null;

		int i = random.nextInt(100);

		switch (i) {
		case 0:
			newItem = (SpawnableItem) new WeaponFactory().createWeapon(WeaponId.MACHINEGUN);
			break;
		case 1:
			newItem = (SpawnableItem) new WeaponFactory().createWeapon(WeaponId.GATLING_GUN);
			break;
		default:
			break;
		}
		if (newItem != null) {
			addItem(newItem);
		}
	}
}
