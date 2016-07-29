package shield;

import Items.ItemParent;
import interfaces.Shield;
import model.PlayerData;

public class ShieldParent extends ItemParent implements Shield{

	private long protection;
	private long maxProtectionValue;
		
	public long getProtection() {
		return protection;
	}

	public void setProtection(long protection) {
		this.protection = protection;
	}

	public long getMaxProtectionValue() {
		return maxProtectionValue;
	}

	public void setMaxProtectionValue(long maxProtectionValue) {
		this.maxProtectionValue = maxProtectionValue;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.setShield(this);		
	}

	@Override
	public void decreaseProtection(long value) {
		this.protection -= value;
		if(this.protection < 0L) this.protection = 0L;	
	}

	@Override
	public void increaseShieldPower() {
		if(protection < maxProtectionValue) protection++;
		
	}
}
