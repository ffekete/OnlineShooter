package interfaces;

public interface Weapon {
	public long getDamage();
	public void decreaseAmmo(long value);
	public boolean hasAmmo();
	public long getRateOfFire();
	public void setRateOfFire(long value);
}
