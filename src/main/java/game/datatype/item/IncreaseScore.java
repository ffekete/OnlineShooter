package game.datatype.item;

import game.config.constant.GameConfig;
import game.datatype.PlayerData;
import game.service.Spawner;

public class IncreaseScore extends ItemParent{
    public IncreaseScore(){
        Spawner.spawn(this);
        this.setName("Score++");
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.increaseScore(GameConfig.ITEM_SCORE_VALUE);
    }
}
