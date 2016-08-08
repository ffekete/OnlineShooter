package game.datatypes.Items;

import game.model.PlayerData;
import game.service.Spawner;

public class IncreaseRateOfFire extends ItemParent{
    
    public IncreaseRateOfFire(){
        Spawner.spawn(this);
        super.setName("Rate of fire +1");
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.getWeapon().increaseRateOfFire(1L);
    }
}
