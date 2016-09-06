package game.datatype.shield;

import game.datatype.PlayerData;
import game.datatype.item.ItemParent;
import game.interfaces.Shield;

public class ShieldParent extends ItemParent implements Shield {

    private double protection;
    private double maxProtectionValue;

    public double getProtection() {
        return protection;
    }

    public void setProtection(double protection) {
        this.protection = protection;
    }

    public double getMaxProtectionValue() {
        return maxProtectionValue;
    }

    public void setMaxProtectionValue(double maxProtectionValue) {
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
    public void decreaseProtection(double value) {
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
    public void increaseShieldPowerBy(double amount) {
        protection += amount;
        if (protection > maxProtectionValue) {
            protection = maxProtectionValue;
        }
    }
}
