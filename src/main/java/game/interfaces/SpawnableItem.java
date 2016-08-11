package game.interfaces;

import game.datatypes.PlayerData;

public interface SpawnableItem extends Spawnable{
    public void applyEffect(PlayerData player);
}
