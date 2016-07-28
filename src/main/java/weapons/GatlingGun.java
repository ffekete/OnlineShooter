package weapons;

import config.WeaponConfig;
import interfaces.Spawnable;
import interfaces.Weapon;
import service.Spawner;

public class GatlingGun implements Spawnable, Weapon{

	private String name;
	
	private long ammo;
	
	private double x;
	private double y;
	
	private long damage;
	
	private long rateOfFire;
	
	@Override
	public long getDamage() {
		// TODO Auto-generated method stub
		return this.damage;
	}

	@Override
	public void decreaseAmmo(long value) {
		if(this.ammo > 0L){
			this.ammo -= value;
			if(this.ammo < 0L) this.ammo = 0L;
		}		
	}

	public long getRateOfFire() {
		return rateOfFire;
	}

	public void setRateOfFire(long rateOfFire) {
		this.rateOfFire = rateOfFire;
	}

	@Override
	public boolean hasAmmo() {
		return this.ammo > 0L;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAmmo() {
		return ammo;
	}

	public void setAmmo(long ammo) {
		this.ammo = ammo;
	}

	public void setDamage(long damage) {
		this.damage = damage;
	}

	public GatlingGun(){
		Spawner.spawn(this);
		this.damage = WeaponConfig.GATLING_GUN_INIT_DAMAGE;
		this.ammo = WeaponConfig.GATLING_GUN_INIT_AMMO;
		this.name = "Gatling gun";
		this.rateOfFire = WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE;
	}
} 
