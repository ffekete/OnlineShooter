package game.interfaces;

public interface Shield {
	public long getProtection();
	
	public void setProtection(long protection);

	public long getMaxProtectionValue();

	public void setMaxProtectionValue(long maxProtectionValue);
	
	public void decreaseProtection(long value);
	
	public void increaseShieldPower();
}
