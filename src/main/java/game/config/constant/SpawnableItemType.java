package game.config.constant;

public enum SpawnableItemType {
    // Weapons
    GATLING_GUN,
    LASER_CANNON,
    DOUBLE_GATLING_GUN,
    SHOTGUN,

    // Power-ups
    INCREASE_DAMAGE,
    INCRASE_RATE_OF_FIRE,
    INCREASE_MANEUVERABILITY,
    INCREASE_SPEED,

    // Shields
    ATOM_SHIELD,
    PLASMA_SHIELD,

    // Other
    HEALTH_PACK,
    INCREASE_SCORE;

    public static int count() {
        return values().length;
    }

    public static SpawnableItemType get(int index) {
        if (index >= 0 && index < count())
            return values()[index];
        else
            throw new IllegalArgumentException();
    }
}