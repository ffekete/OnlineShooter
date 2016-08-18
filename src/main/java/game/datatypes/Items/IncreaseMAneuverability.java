package game.datatypes.Items;

import game.config.constants.ItemConfig;
import game.datatypes.PlayerData;
import game.service.Spawner;

public class IncreaseMAneuverability extends ItemParent{
    public IncreaseMAneuverability(){
        Spawner.spawn(this);
        super.setName("Maneuverability +1");
    }
    @Override
    public void applyEffect(PlayerData player) {
        player.increaseManeuverablility(ItemConfig.MANEUVERABILITY_INCREASE_VALE);
    }

}
