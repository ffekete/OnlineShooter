package game.config.constant;

public enum SpawnableItemType {
    // Weapons
    GATLING_GUN("Gatling Gun"),
    LASER_CANNON("Laser Cannon"),
    DOUBLE_GATLING_GUN("Double Gatling Gun"),
    SHOTGUN("Shotgun"),

    // Power-ups
    INCREASE_DAMAGE("Damage +1"),
    INCRASE_RATE_OF_FIRE("Rate Of Fire +1"),
    INCREASE_MANEUVERABILITY("Maneuverability +1"),
    INCREASE_SPEED("Speed +1"),

    // Shields
    NORMAL_SHIELD("Normal Shield"),
    ATOM_SHIELD("Atom Shield"),
    PLASMA_SHIELD("Plasma Shield"),

    // Other
    HEALTH_PACK("Health Pack +5"),
    INCREASE_SCORE("Score++");
    
    private final String visibleName;
    
    public static int count() {
        return values().length;
    }
    
    private SpawnableItemType(String visibleName) {
        this.visibleName = visibleName;
    }
    
    public static SpawnableItemType get(int index) {
        if (index >= 0 && index < count())
            return values()[index];
        else
            throw new IllegalArgumentException();
    }
    
    public String getVisibleName() {
        return this.visibleName;
    }
}