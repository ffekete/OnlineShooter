 package game.interfaces;

import java.util.List;

import game.config.constant.ItemType;
import game.datatype.PlayerData;

public interface Weapon {
	
	public ItemType getType();
	
	public void setType(ItemType type);
	
    public long getDamage();

    public void decreaseAmmo(long value);
    
    public void addAmmo(long ammo);

    public boolean hasAmmo();

    public long getRateOfFire();

    public void setRateOfFire(long value);

    public void decreaseRateOfFireCooldownValue(long value);

    public void startShootingRateCooldownEffect();

    public boolean canShoot();

    public void increaseDamage(long amount);

    public void increaseRateOfFire(long amount);

    public long getAmmo();

    public long getRateOfFireCooldown();

    public List<Bullet> createBullet(PlayerData player);
}
