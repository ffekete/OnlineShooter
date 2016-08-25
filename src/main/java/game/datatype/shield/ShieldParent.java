package game.datatype.shield;

import game.datatype.PlayerData;
import game.datatype.item.ItemParent;
import game.interfaces.Shield;

public class ShieldParent extends ItemParent implements Shield {

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
        if (this.maxProtectionValue > player.getShield().getMaxProtectionValue()) {
            player.setShield(this);
        } else {
            player.getShield().increaseShieldPowerBy(this.getProtection());
        }
    }

    @Override
    public void decreaseProtection(long value) {
        this.protection -= value;
        if (this.protection < 0L)
            this.protection = 0L;
    }

    @Override
    public void increaseShieldPower() {
        if (protection < maxProtectionValue) {
            protection++;
        }
    }

    @Override
    public void increaseShieldPowerBy(long amount) {
        protection += amount;
        if (protection > maxProtectionValue) {
            protection = maxProtectionValue;
        }
    }
}
