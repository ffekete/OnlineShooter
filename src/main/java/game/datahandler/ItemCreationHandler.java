package game.datahandler;

import factory.ShieldFactory;
import factory.WeaponFactory;
import game.config.constant.SpawnableItemType;
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
    public SpawnableItem createItem(SpawnableItemType type) {
        SpawnableItem item = null;
        switch (type) {
        // weapons
        case GATLING_GUN:
            item = (SpawnableItem) WeaponFactory.createWeapon(SpawnableItemType.GATLING_GUN);
            break;
        case LASER_CANNON:
            item = (SpawnableItem) WeaponFactory.createWeapon(SpawnableItemType.LASER_CANNON);
            break;
        case DOUBLE_GATLING_GUN:
            item = (SpawnableItem) WeaponFactory.createWeapon(SpawnableItemType.DOUBLE_GATLING_GUN);
            break;
        case SHOTGUN:
            item = (SpawnableItem) WeaponFactory.createWeapon(SpawnableItemType.SHOTGUN);
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
            item = (SpawnableItem) ShieldFactory.createShield(SpawnableItemType.NORMAL_SHIELD);
            break;
        case PLASMA_SHIELD:
            item = (SpawnableItem) ShieldFactory.createShield(SpawnableItemType.PLASMA_SHIELD);
            break;
        case ATOM_SHIELD:
            item = (SpawnableItem) ShieldFactory.createShield(SpawnableItemType.ATOM_SHIELD);
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
        return createItem(SpawnableItemType.get(RandomGenerator.getRandomInRange(0, SpawnableItemType.count() - 1)));
    }
}
