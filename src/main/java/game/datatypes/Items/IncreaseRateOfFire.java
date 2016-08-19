package game.datatypes.Items;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.datatypes.PlayerData;
import game.service.Spawner;

@Component
public class IncreaseRateOfFire extends ItemParent {

    @Autowired
    private MessageSource messages;

    public IncreaseRateOfFire() {
        Spawner.spawn(this);
        super.setName(messages.getMessage("item.increaserof", null, Locale.US));
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.getWeapon().increaseRateOfFire(1L);
    }
}
