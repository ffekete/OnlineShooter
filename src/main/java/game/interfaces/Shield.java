package game.interfaces;

public interface Shield {
    public double getProtection();

    public void setProtection(double protection);

    public double getMaxProtectionValue();

    public void setMaxProtectionValue(double maxProtectionValue);

    public void decreaseProtection(double value);

    public void increaseShieldPower();

    public void increaseShieldPowerBy(double amount);
}
