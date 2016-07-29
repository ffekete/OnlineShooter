package Items;

import model.PlayerData;
import service.Spawner;

public class IncreaseSpeed extends ItemParent{
	public IncreaseSpeed(){
		Spawner.spawn(this);
		this.setName("Speed +1");
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.increaseSpeed(0.25d);
	}
}
