package interfaces;

import model.PlayerData;

public interface SpawnableItem extends Spawnable{
	public void applyEffect(PlayerData player);
}
