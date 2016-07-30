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
import Items.IncreaseScore;
import Items.IncreaseSpeed;
import config.CanvasConstants;
import config.GameConfig;
import config.ShieldId;
import config.WeaponId;
import factory.ShieldFactory;
import factory.WeaponFactory;
import interfaces.SpawnableItem;
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
			if ((Math.abs(item.getX() - playerData.getX()) <= playerData.getCanvas().getHalfWidth())
					&& (Math.abs(item.getY() - playerData.getY()) <= playerData.getCanvas().getHalfHeight())) {
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
			case 8:
			case 9:
			case 10:
			case 11:
				newItem = (SpawnableItem) new IncreaseScore();
				break;				
			case 12:
				newItem = (SpawnableItem) new ShieldFactory().createShield(ShieldId.ATOM_SHIELD);
				break;
			case 13:
				newItem = (SpawnableItem) new ShieldFactory().createShield(ShieldId.PLASMA_SHIELD);
				break;
			case 14:
				newItem = (SpawnableItem) new WeaponFactory().createWeapon(WeaponId.DOUBLE_GATLING);
				break;
			case 15:
				newItem = (SpawnableItem) new WeaponFactory().createWeapon(WeaponId.SHOTGUN);
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
