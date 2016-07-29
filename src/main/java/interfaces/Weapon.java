package interfaces;

import java.util.List;

import model.BulletData;
import model.PlayerData;

public interface Weapon {
	public long getDamage();
	public void decreaseAmmo(long value);
	public boolean hasAmmo();
	public long getRateOfFire();
	public void setRateOfFire(long value);
	public void decreaseRateOfFireCooldownValue(long value);
	public void startShootingRateCooldownEffect();
	public boolean canShoot();
	public void increaseDamage(long amount);
	public void increaseRateOfFire(long amount);
	public long getAmmo();
	public List<BulletData> createBullet(PlayerData player);
}
