package game.datatypes.Items;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.datatypes.PlayerData;
import game.service.Spawner;

@Component
public class HealthPack extends ItemParent {

    @Autowired
    private MessageSource messages;

    public HealthPack() {
        Spawner.spawn(this);
        super.setName(messages.getMessage("item.increasehealth", null, Locale.US));
    }

    @Override
    public void applyEffect(PlayerData player) {
        long actualHp = player.getHp();
        player.setHp(actualHp + 5);
    }
}
