package game.config.constant;

public enum AmmoType {
    // Kinetic types
    BULLET(2.0, 0.25, 150.0, 20.0, 20.0, 0.0, 0.0, 0.0), 
    CANISTER(1.0, 0.2, 100.0, 20.0, 20.0, 0.0, 0.0, 0.0),

    // Energy types
    LASER_BEAM(10.0, 2.0, 5.0, 0.0, 0.0, 5.0, 1000.0, 0.0),

    // Explosive types
    MISSILE(20.0, 4.0, 100.0, 5.0, 25.0, 50.0, 0.0, 0.5), 
    HEAT_SEEKING_MISSILE(20.0, 4.0, 75.0, 5.0, 25.0, 50.0, 0.0, 0.5),

    NO_AMMO(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

    private double initDamage;
    private double damageBonus;
    private double initAge;
    private double initSpeed;
    private double initHitRadius;
    private double length;
    private double maxSpeed;
    private double acceleration;

    private AmmoType(double initDamage, double damageBonus, double initAge, double initSpeed, double maxSpeed,
            double initHitRadius, double length, double acceleration) {
        this.initDamage = initDamage;
        this.damageBonus = damageBonus;
        this.initAge = initAge;
        this.initSpeed = initSpeed;
        this.maxSpeed = maxSpeed;
        this.initHitRadius = initHitRadius;
        this.length = length;
        this.acceleration = acceleration;
    }

    public double getDamage(long bonus) {
        if (bonus > 0) {
            return this.initDamage + this.damageBonus * bonus;
        } else {
            return this.initDamage;
        }
    }

    public static int count() {
        return values().length;
    }

    public static AmmoType get(int index) {
        if (index >= 0 && index < count())
            return values()[index];
        else
            throw new IllegalArgumentException();
    }

    public double getInitAge() {
        return this.initAge;
    }

    public double getInitSpeed() {
        return this.initSpeed;
    }

    public double getInitHitRadius() {
        return this.initHitRadius;
    }

    public double getLength() {
        return this.length;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public double getAcceleration() {
        return this.acceleration;
    }
}