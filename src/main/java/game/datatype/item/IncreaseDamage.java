package game.datatype.item;

import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;
import game.service.Spawner;

public class IncreaseDamage extends ItemParent {
    public IncreaseDamage() {
        Spawner.spawn(this);
        super.setName(SpawnableItemType.INCREASE_DAMAGE.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.getWeapon().increaseDamage(1L);
    }
}
