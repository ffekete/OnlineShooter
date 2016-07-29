package Items;

import config.GameConfig;
import interfaces.SpawnableItem;
import model.PlayerData;
import service.Spawner;

public class IncreaseScore implements SpawnableItem{
	private String name;
	
	private double x;
	private double y;
	
	public IncreaseScore(){
		Spawner.spawn(this);
		this.name = "Score++";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.increaseScore(GameConfig.ITEM_SCORE_VALUE);
	}

	@Override
	public void setX(double x) {
		this.x = x;
		
	}

	@Override
	public void setY(double y) {
		this.y = y;
		
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}
}
