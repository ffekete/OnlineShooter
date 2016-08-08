package game.datatypes.Items;

import game.config.GameConfig;
import game.model.PlayerData;
import game.service.Spawner;

public class IncreaseScore extends ItemParent{
	public IncreaseScore(){
		Spawner.spawn(this);
		this.setName("Score++");
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.increaseScore(GameConfig.ITEM_SCORE_VALUE);
	}
}
