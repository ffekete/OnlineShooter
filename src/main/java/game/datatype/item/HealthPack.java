package game.datatype.item;

import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;
import game.service.Spawner;

public class HealthPack extends ItemParent {

    public HealthPack() {
        Spawner.spawn(this);
        super.setName(SpawnableItemType.HEALTH_PACK.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        long actualHp = player.getHp();
        player.setHp(actualHp + 5);
    }
}
