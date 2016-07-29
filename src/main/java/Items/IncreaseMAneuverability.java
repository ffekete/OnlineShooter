package Items;

import model.PlayerData;
import service.Spawner;

public class IncreaseMAneuverability extends ItemParent{
	public IncreaseMAneuverability(){
		Spawner.spawn(this);
		super.setName("Meneuverability +1");
	}
	@Override
	public void applyEffect(PlayerData player) {
		player.increaseManeuverablility(0.5d);
	}

}
