package game.datatype.item;

import game.config.constant.ItemConfig;
import game.config.constant.ItemType;
import game.datatype.PlayerData;
import game.service.Spawner;
import game.util.RandomGenerator;

public class HealthPack extends ItemParent {
	private int health;

    public HealthPack() {
    	this.health = RandomGenerator.getRandomInRange(
    			ItemConfig.HEALTH_PACK_VALUE_MIN,
    			ItemConfig.HEALTH_PACK_VALUE_MAX);
        Spawner.spawn(this);
        super.setName(ItemType.HEALTH_PACK.getVisibleName() + this.health);
    }

    @Override
    public void applyEffect(PlayerData player) {
        long actualHp = player.getHp();
        player.setHp(actualHp + this.health);
    }
}
