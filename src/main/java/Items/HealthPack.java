package Items;

import interfaces.SpawnableItem;
import model.PlayerData;
import service.Spawner;

public class HealthPack implements SpawnableItem{
	private String name;
	
	private double x;
	private double y;
	
	public HealthPack(){
		Spawner.spawn(this);
		this.name = "Health +5";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void applyEffect(PlayerData player) {
		long actualHp = player.getHp();
		player.setHp(actualHp + 5);
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
