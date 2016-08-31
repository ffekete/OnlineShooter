package game.datatype.item;

import game.config.constant.GameConfig;
import game.config.constant.ItemType;
import game.datatype.PlayerData;
import game.service.Spawner;

public class IncreaseScore extends ItemParent {
    public IncreaseScore() {
        Spawner.spawn(this);
        this.setName(ItemType.INCREASE_SCORE.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.increaseScore(GameConfig.ITEM_SCORE_VALUE);
    }
}
