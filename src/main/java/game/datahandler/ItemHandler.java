package game.datahandler;

import org.springframework.stereotype.Component;

import factory.ShieldFactory;
import factory.WeaponFactory;
import game.config.ShieldId;
import game.config.WeaponId;
import game.config.constants.SpawnableItemTypeConstants;
import game.datatypes.PlayerData;
import game.datatypes.Items.HealthPack;
import game.datatypes.Items.IncreaseDamage;
import game.datatypes.Items.IncreaseMAneuverability;
import game.datatypes.Items.IncreaseRateOfFire;
import game.datatypes.Items.IncreaseScore;
import game.datatypes.Items.IncreaseSpeed;
import game.interfaces.ItemCreation;
import game.interfaces.Spawnable;
import game.interfaces.SpawnableItem;

@Component
public class ItemHandler {

    public boolean isItOnScreen(PlayerData player, Spawnable item) {
        boolean result = false;

        if ((Math.abs(item.getX() - player.getX()) <= player.getScreenHalfWidth())
                && (Math.abs(item.getY() - player.getY()) <= player.getScreenHalfHeight())) {
            result = true;
        }

        return result;
    }
}