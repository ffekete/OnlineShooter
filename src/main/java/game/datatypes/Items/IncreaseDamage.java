package game.datatypes.Items;

import game.model.PlayerData;
import game.service.Spawner;

public class IncreaseDamage extends ItemParent{
    public IncreaseDamage(){
        Spawner.spawn(this);
        super.setName("Damage +1");
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.getWeapon().increaseDamage(1L);
    }
}
