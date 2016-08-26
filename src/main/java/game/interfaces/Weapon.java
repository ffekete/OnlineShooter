 package game.interfaces;

import java.util.List;

import game.config.constant.AmmoType;
import game.config.constant.SpawnableItemType;
import game.datatype.PlayerData;

public interface Weapon {
	
	public SpawnableItemType getType();
	
	public void setType(SpawnableItemType type);
	
    public AmmoType getAmmoType();

	public void setAmmoType(AmmoType ammoType);
	
	public long getInitAmmoCount();

    public double getRateOfFire();

    public void setRateOfFire(double value);
    
    public void increaseRateOfFire(long bonus);
    
    public void increaseDamage(long bonus);
    
    public double getCooldown();

    public void decreaseCooldownValue(double value);

    public void startCooldownEffect();
    
    boolean isReadyToShoot();

	long getShotCount();

	void setShotCount(long shotCount);

	double getShotAngle();

	void setShotAngle(double shotAngle);

	void setCooldown(double cooldown);
	
	public double getDamage();
	
	void setDamage(double damage);

	List<Ammo> createAmmo(PlayerData player);

	public void applyBonuses(PlayerData playerData);
	
	public double getPower();
}
