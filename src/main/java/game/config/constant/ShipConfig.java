package game.config.constant;

public enum ShipConfig {
    QUICKSILVER("Quicksilver", 30L, 6.0D, 6.0D, 10.0D, ItemType.SHOTGUN, 0, 15.0, ItemType.NORMAL_SHIELD), 
    MERCURY("Mercury", 50L, 5.0D, 5.0D, 12.0D, ItemType.GATLING_GUN, 0, 15, ItemType.NORMAL_SHIELD),
    INTERCEPTOR("Interceptor", 40L, 7.0D, 7.0D, 14.0D, ItemType.LASER_CANNON, 0, 15.0, ItemType.NORMAL_SHIELD),
    DELTAWING("Deltawing", 25L, 9.0D, 9.0D, 16.0D, ItemType.DOUBLE_GATLING_GUN, 0, 15.0, ItemType.NORMAL_SHIELD),
    CARGOSHIP("Cargoship", 80L, 3.0D, 3.0D, 10.0D, ItemType.NO_WEAPON, 10, 20.0, ItemType.NORMAL_SHIELD),
    ASTEROID("Asteroid", 100L, 3.0D, 7.0D, 1.0D, ItemType.NO_WEAPON, 0, 20.0, ItemType.NO_SHIELD);

    private final String type;
    private final Long maxHP;
    private final double initMinSpeed;
    private final double initMaxSpeed;
    private final double initManeuverability;
    private final ItemType initWeapon;
    private final int cargoCapacity;
    private final double hitRadius;
    private final ItemType initShield;

    private ShipConfig(String type, Long maxHP, double initMinSpeed, double initMaxSpeed, double initManeuverability,
            ItemType initWeapon, int cargoCapacity, double hitRadius, ItemType initShield) {
        this.type = type;
        this.maxHP = maxHP;
        this.initMaxSpeed = initMaxSpeed;
        this.initMinSpeed = initMinSpeed;
        this.initManeuverability = initManeuverability;
        this.initWeapon = initWeapon;
        this.cargoCapacity = cargoCapacity;
        this.hitRadius = hitRadius;
        this.initShield = initShield;
    }

    public String getType() {
        return this.type;
    }

    public Long getMaxHP() {
        return this.maxHP;
    }

    public double getInitMinSpeed() {
        return this.initMinSpeed;
    }

    public double getInitMaxSpeed() {
        return this.initMaxSpeed;
    }

    public double getInitManeuverability() {
        return this.initManeuverability;
    }

    public ItemType getInitWeapon() {
        return this.initWeapon;
    }

    public int getCargoCapacity() {
        return this.cargoCapacity;
    }

    public double getHitRadius() {
        return hitRadius;
    }

    public ItemType getInitShield() {
        return this.initShield;
    }

    public static ShipConfig getSpecificConfig(String shipType) {
        for (ShipConfig sc : values()) {
            if (shipType.equals(sc.getType())) {
                return sc;
            }
        }
        throw new NullPointerException("Bad ship type! (" + shipType + ")");
    }
}
