package weapons;

import interfaces.Spawnable;
import interfaces.Weapon;

public abstract class WeaponParent implements Spawnable, Weapon {
	private long rateOfFireCooldown;

	private long rateOfFire;
	
	private String name;
	
	private long ammo;
	
	private double x;
	private double y;
	
	private long damage;
	
	public boolean canShoot(){
		return this.hasAmmo() && this.rateOfFireCooldown < 1L;
	}
	
	public long removeAmmo(long amount){
		if(amount > this.ammo){
			this.ammo -= amount;
			return amount;
		}
		else
		{
			return amount - this.ammo;
		}
	}
	
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
	
	public long getRateOfFire() {
		return rateOfFire;
	}

	
	
	public void setRateOfFire(long rateOfFire) {
		this.rateOfFire = rateOfFire;
	}
	
	public long getRateOfFireCooldown() {
		return rateOfFireCooldown;
	}

	public void setRateOfFireCooldown(long rateOfFireCooldown) {
		this.rateOfFireCooldown = rateOfFireCooldown;
	}
	
	public void startShootingRateCooldownEffect(){
		rateOfFireCooldown = this.getRateOfFire();
	}
	
	public void decreaseRateOfFireCooldownValue(long value){
		if(rateOfFireCooldown > 0L)
		{
			rateOfFireCooldown -= value;
			if(rateOfFireCooldown < 0L) rateOfFireCooldown = 0L;
		}
	}
}
