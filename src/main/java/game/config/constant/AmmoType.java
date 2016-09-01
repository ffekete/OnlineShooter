package game.config.constant;

public enum AmmoType {
    // Kinetic types
    BULLET(2.0, 0.25, 150.0, 20.0, 0.0, 0.0), 
    CANISTER(1.0, 0.2, 100.0, 20.0, 0.0, 0.0),

    // Energy types
    LASER_BEAM(10.0, 2.0, 5.0, 0.0, 5.0, 1000.0),

    // Explosive types
    HEAT_SEEKING_MISSILE(20.0, 4.0, 200.0, 5.0, 50.0, 0.0),

    NO_AMMO(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

    private double initDamage;
    private double damageBonus;
    private double initAge;
    private double initSpeed;
    private double initHitRadius;
    private double length;

    private AmmoType(double initDamage, double damageBonus, double initAge, double initSpeed, double initHitRadius,
            double length) {
        this.initDamage = initDamage;
        this.damageBonus = damageBonus;
        this.initAge = initAge;
        this.initSpeed = initSpeed;
        this.initHitRadius = initHitRadius;
        this.length = length;
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
}