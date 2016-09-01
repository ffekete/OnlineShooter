package game.config.constant;

public enum WeaponConfig {
    GATLING_GUN(400L, 1L, 0.0, 20.0, 2.0, 60.0),
    DOUBLE_GATLING_GUN(400L, 2L, 0.5, 20.0, 2.0, 60.0),
    SHOTGUN(400L, 10L, 2.0, 2.0, 0.5, 60.0),
    LASER_CANNON(50L, 1L, 0.0, 3.0, 0.5, 60.0),
    NO_WEAPON(0L, 0L, 0.0, 0.0, 0.0, 0.0);

    private Long initAmmoCount;
    private Long initShotCount;
    private double initShotAngle;
    private double initRateOfFire;
    private double rateOfFireBonus;
    private double rateOfFireTimeesCooldown;

    private WeaponConfig(Long initAmmoCount, Long initShotCount, double initShotAngle, double initRateOfFire,
            double rateOfFireBonus, double rateOfFireTimeesCooldown) {
        this.initAmmoCount = initAmmoCount;
        this.initShotCount = initShotCount;
        this.initShotAngle = initShotAngle;
        this.initRateOfFire = initRateOfFire;
        this.rateOfFireBonus = rateOfFireBonus;
        this.rateOfFireTimeesCooldown = rateOfFireTimeesCooldown;
    }

    public Long getInitAmmoCount() {
        return initAmmoCount;
    }

    public Long getInitShotCount() {
        return initShotCount;
    }

    public double getInitShotAngle() {
        return initShotAngle;
    }

    public double getInitRateOfFire() {
        return initRateOfFire;
    }

    public double getRateOfFireBonus() {
        return rateOfFireBonus;
    }

    public double getRateOfFireTimeesCooldown() {
        return rateOfFireTimeesCooldown;
    }
}