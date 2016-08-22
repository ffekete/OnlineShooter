package game.datatype.item;

import game.datatype.PlayerData;
import game.service.Spawner;

public class HealthPack extends ItemParent{
    
    public HealthPack(){
        Spawner.spawn(this);
        super.setName("Health +5");
    }

    @Override
    public void applyEffect(PlayerData player) {
        long actualHp = player.getHp();
        player.setHp(actualHp + 5);
    }
}
