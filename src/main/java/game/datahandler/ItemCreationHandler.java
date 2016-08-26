package game.datahandler;

import factory.ShieldFactory;
import factory.WeaponFactory;
import game.config.constant.ItemType;
import game.datatype.item.HealthPack;
import game.datatype.item.IncreaseDamage;
import game.datatype.item.IncreaseManeuverability;
import game.datatype.item.IncreaseRateOfFire;
import game.datatype.item.IncreaseScore;
import game.datatype.item.IncreaseSpeed;
import game.interfaces.ItemCreation;
import game.interfaces.SpawnableItem;
import game.util.RandomGenerator;

public class ItemCreationHandler implements ItemCreation {
    @Override
    public SpawnableItem createItem(ItemType type) {
        SpawnableItem item = null;
        switch (type) {
        // weapons
        case GATLING_GUN:
            item = (SpawnableItem) WeaponFactory.createWeapon(ItemType.GATLING_GUN);
            break;
        case LASER_CANNON:
            item = (SpawnableItem) WeaponFactory.createWeapon(ItemType.LASER_CANNON);
            break;
        case DOUBLE_GATLING_GUN:
            item = (SpawnableItem) WeaponFactory.createWeapon(ItemType.DOUBLE_GATLING_GUN);
            break;
        case SHOTGUN:
            item = (SpawnableItem) WeaponFactory.createWeapon(ItemType.SHOTGUN);
            break;
        // power up-s
        case INCREASE_DAMAGE:
            item = (SpawnableItem) new IncreaseDamage();
            break;
        case INCRASE_RATE_OF_FIRE:
            item = (SpawnableItem) new IncreaseRateOfFire();
            break;
        case INCREASE_MANEUVERABILITY:
            item = (SpawnableItem) new IncreaseManeuverability();
            break;
        case INCREASE_SPEED:
            item = (SpawnableItem) new IncreaseSpeed();
            break;
        // shields
        case NORMAL_SHIELD:
            item = (SpawnableItem) ShieldFactory.createShield(ItemType.NORMAL_SHIELD);
            break;
        case PLASMA_SHIELD:
            item = (SpawnableItem) ShieldFactory.createShield(ItemType.PLASMA_SHIELD);
            break;
        case ATOM_SHIELD:
            item = (SpawnableItem) ShieldFactory.createShield(ItemType.ATOM_SHIELD);
            break;
        // other
        case HEALTH_PACK:
            item = (SpawnableItem) new HealthPack();
            break;
        case INCREASE_SCORE:
            item = (SpawnableItem) new IncreaseScore();
            break;
        }
        return item;
    }

    public SpawnableItem createRandomItem() {
        return createItem(ItemType.get(RandomGenerator.getRandomInRange(0, ItemType.count() - 3)));
    }
}
