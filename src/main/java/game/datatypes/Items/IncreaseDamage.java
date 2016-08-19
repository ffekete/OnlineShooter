package game.datatypes.Items;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.datatypes.PlayerData;
import game.service.Spawner;

@Component
public class IncreaseDamage extends ItemParent {

    @Autowired
    private MessageSource messages;

    public IncreaseDamage() {
        Spawner.spawn(this);
        super.setName(messages.getMessage("item.increasedamage", null, Locale.US));
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.getWeapon().increaseDamage(1L);
    }
}
