package game.datatype.item;

import game.config.constant.Bonuses;
import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;
import game.service.Spawner;

public class IncreaseRateOfFire extends ItemParent {

    public IncreaseRateOfFire() {
        Spawner.spawn(this);
        super.setName(SpawnableItemType.INCRASE_RATE_OF_FIRE.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.updateBonus(Bonuses.RATE_OF_FIRE, 1L);
        player.getWeapon().increaseRateOfFire(1L);
    }
}
