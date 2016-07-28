package Items;

import interfaces.SpawnableItem;
import model.PlayerData;
import service.Spawner;

public class IncreaseSpeed implements SpawnableItem{
	private String name;
	
	private double x;
	private double y;
	
	public IncreaseSpeed(){
		Spawner.spawn(this);
		this.name = "Speed +1";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.increaseSpeed(0.25d);
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
