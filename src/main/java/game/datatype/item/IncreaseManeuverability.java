package game.datatype.item;

import game.config.constant.ItemConfig;
import game.config.constant.ItemType;
import game.datatype.PlayerData;
import game.service.Spawner;

public class IncreaseManeuverability extends ItemParent {
    public IncreaseManeuverability() {
        Spawner.spawn(this);
        super.setName(ItemType.INCREASE_MANEUVERABILITY.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.increaseManeuverablility(ItemConfig.MANEUVERABILITY_INCREASE_VALUE);
    }

}
