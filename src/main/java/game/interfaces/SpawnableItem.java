package game.interfaces;

import game.datatype.PlayerData;

public interface SpawnableItem extends Spawnable{
    public void applyEffect(PlayerData player);
}
