package game.interfaces;

import game.model.PlayerData;

public interface SpawnableItem extends Spawnable{
    public void applyEffect(PlayerData player);
}
