package game.datatype.item;

import game.config.constant.Bonuses;
import game.config.constant.ItemType;
import game.datatype.PlayerData;
import game.interfaces.Weapon;
import game.service.Spawner;

public class IncreaseRateOfFire extends ItemParent {

    public IncreaseRateOfFire() {
        Spawner.spawn(this);
        super.setName(ItemType.INCRASE_RATE_OF_FIRE.getVisibleName());
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.increaseBonus(Bonuses.RATE_OF_FIRE, 1L);
        for(Weapon weapon : player.getSpaceShip().getWeapons()) {
        	weapon.increaseRateOfFire(player.getRateOfFireBonus());
        }
    }
}
