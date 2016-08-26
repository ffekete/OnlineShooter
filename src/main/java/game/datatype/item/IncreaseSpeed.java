package game.datatype.item;

import game.config.constant.ItemConfig;
import game.config.constant.ItemType;
import game.datatype.PlayerData;
import game.service.Spawner;

public class IncreaseSpeed extends ItemParent {
    public IncreaseSpeed() {
        Spawner.spawn(this);
        this.setName(ItemType.INCREASE_SPEED.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.increaseSpeed(ItemConfig.INCREASE_SPEED_VALUE);
    }
}
