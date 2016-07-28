package Items;

import interfaces.SpawnableItem;
import model.PlayerData;
import service.Spawner;

public class IncreaseRateOfFire implements SpawnableItem{
	private String name;
	
	private double x;
	private double y;
	
	public IncreaseRateOfFire(){
		Spawner.spawn(this);
		this.name = "Rate of fire +1";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.getWeapon().increaseDamage(1L);
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
