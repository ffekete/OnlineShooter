package game.datatypes.Items;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import game.config.constants.GameConfig;
import game.datatypes.PlayerData;
import game.service.Spawner;

@Component
public class IncreaseScore extends ItemParent {

    @Autowired
    private MessageSource messages;

    public IncreaseScore() {
        Spawner.spawn(this);
        this.setName(messages.getMessage("item.increasescore", null, Locale.US));
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.increaseScore(GameConfig.ITEM_SCORE_VALUE);
    }
}
