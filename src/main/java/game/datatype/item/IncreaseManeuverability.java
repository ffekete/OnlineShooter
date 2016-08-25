package game.datatype.item;

import game.config.constant.ItemConfig;
import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;
import game.service.Spawner;

public class IncreaseManeuverability extends ItemParent {
    public IncreaseManeuverability() {
        Spawner.spawn(this);
        super.setName(SpawnableItemType.INCREASE_MANEUVERABILITY.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.increaseManeuverablility(ItemConfig.MANEUVERABILITY_INCREASE_VALE);
    }

}
