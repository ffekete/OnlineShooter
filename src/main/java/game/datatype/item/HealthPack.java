package game.datatype.item;

import game.config.constant.ItemType;
import game.datatype.PlayerData;
import game.service.Spawner;

public class HealthPack extends ItemParent {

    public HealthPack() {
        Spawner.spawn(this);
        super.setName(ItemType.HEALTH_PACK.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        long actualHp = player.getHp();
        player.setHp(actualHp + 5);
    }
}
