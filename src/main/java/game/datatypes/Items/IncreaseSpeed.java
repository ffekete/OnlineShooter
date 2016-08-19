package game.datatypes.Items;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.config.constants.ItemConfig;
import game.datatypes.PlayerData;
import game.service.Spawner;

@Component
public class IncreaseSpeed extends ItemParent {

    @Autowired
    private MessageSource messages;

    public IncreaseSpeed() {
        Spawner.spawn(this);
        this.setName(messages.getMessage("item.increasespeed", null, Locale.US));
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.increaseSpeed(ItemConfig.INCREASE_SPEED_VALUE);
    }
}
