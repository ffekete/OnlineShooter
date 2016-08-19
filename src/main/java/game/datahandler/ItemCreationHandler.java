package game.datahandler;

import factory.ShieldFactory;
import factory.WeaponFactory;
import game.config.ShieldId;
import game.config.WeaponId;
import game.config.constants.SpawnableItemTypeConstants;
import game.datatypes.Items.HealthPack;
import game.datatypes.Items.IncreaseDamage;
import game.datatypes.Items.IncreaseMAneuverability;
import game.datatypes.Items.IncreaseRateOfFire;
import game.datatypes.Items.IncreaseScore;
import game.datatypes.Items.IncreaseSpeed;
import game.interfaces.ItemCreation;
import game.interfaces.SpawnableItem;

public class ItemCreationHandler implements ItemCreation {
    @Override
    public SpawnableItem createItem(int id) {
        SpawnableItem item = null;
        switch (id) {
        case SpawnableItemTypeConstants.GATLING_GUN:
            item = (SpawnableItem) WeaponFactory.createWeapon(WeaponId.GATLING_GUN);
            break;
        case SpawnableItemTypeConstants.INCREASE_DAMAGE:
            item = (SpawnableItem) new IncreaseDamage();
            break;
        case SpawnableItemTypeConstants.HEALTH_PACK:
            item = (SpawnableItem) new HealthPack();
            break;
        case SpawnableItemTypeConstants.INCRASE_RATE_OF_FIRE:
            item = (SpawnableItem) new IncreaseRateOfFire();
            break;
        case SpawnableItemTypeConstants.LASER_CANNON:
            item = (SpawnableItem) WeaponFactory.createWeapon(WeaponId.LASER_CANNON);
            break;
        case SpawnableItemTypeConstants.INCREASE_MANEUVERABILITY:
            item = (SpawnableItem) new IncreaseMAneuverability();
            break;
        case SpawnableItemTypeConstants.INCREASE_SPEED:
            item = (SpawnableItem) new IncreaseSpeed();
            break;
        case SpawnableItemTypeConstants.INCREASE_SCORE:
            item = (SpawnableItem) new IncreaseScore();
            break;
        case SpawnableItemTypeConstants.ATOM_SHIELD:
            item = (SpawnableItem) ShieldFactory.createShield(ShieldId.ATOM_SHIELD);
            break;
        case SpawnableItemTypeConstants.PLASMA_SHIELD:
            item = (SpawnableItem) ShieldFactory.createShield(ShieldId.PLASMA_SHIELD);
            break;
        case SpawnableItemTypeConstants.DOUBLE_GATLING_GUN:
            item = (SpawnableItem) WeaponFactory.createWeapon(WeaponId.DOUBLE_GATLING);
            break;
        case SpawnableItemTypeConstants.SHOTGUN:
            item = (SpawnableItem) WeaponFactory.createWeapon(WeaponId.SHOTGUN);
            break;
        }
        return item;
    }
}
