package game.datatype.item;

import game.config.constant.Bonuses;
import game.config.constant.ItemType;
import game.datatype.PlayerData;
import game.service.Spawner;

public class IncreaseDamage extends ItemParent {
    public IncreaseDamage() {
        Spawner.spawn(this);
        super.setName(ItemType.INCREASE_DAMAGE.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.updateBonus(Bonuses.DAMAGE, 1L);
        player.getWeapon().increaseDamage(1L);
    }
}
