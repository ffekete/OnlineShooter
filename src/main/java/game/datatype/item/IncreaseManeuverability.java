package game.datatype.item;

import game.config.constant.ItemConfig;
import game.datatype.PlayerData;
import game.service.Spawner;

public class IncreaseManeuverability extends ItemParent{
    public IncreaseManeuverability(){
        Spawner.spawn(this);
        super.setName("Maneuverability +1");
    }
    @Override
    public void applyEffect(PlayerData player) {
        player.increaseManeuverablility(ItemConfig.MANEUVERABILITY_INCREASE_VALE);
    }

}
