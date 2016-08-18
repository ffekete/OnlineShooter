package game.datatypes.Items;

import game.config.constants.ItemConfig;
import game.datatypes.PlayerData;
import game.service.Spawner;

public class IncreaseSpeed extends ItemParent{
    public IncreaseSpeed(){
        Spawner.spawn(this);
        this.setName("Speed +1");
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.increaseSpeed(ItemConfig.INCREASE_SPEED_VALUE);
    }
}
