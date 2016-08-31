package game.config.constant;

import java.util.ResourceBundle;

public enum ItemType {
    // Weapons
    GATLING_GUN("gatlingGun"),
    LASER_CANNON("laserCannon"),
    DOUBLE_GATLING_GUN("doubleGatlingGun"),
    SHOTGUN("shotgun"),

    // Power-ups
    INCREASE_DAMAGE("increaseDamage"),
    INCRASE_RATE_OF_FIRE("increaseRof"),
    INCREASE_MANEUVERABILITY("increaseManeuverability"),
    INCREASE_SPEED("increaseSpeed"),

    // Shields
    NORMAL_SHIELD("normalShield"),
    ATOM_SHIELD("atomShield"),
    PLASMA_SHIELD("plasmaShield"),
    
    // Other
    HEALTH_PACK("healthPack"),
    INCREASE_SCORE("increaseScore"),
    
    NO_SHIELD("noShield"),
    NO_WEAPON("noWeapon");
    
    private static final ResourceBundle res = ResourceBundle.getBundle("com.example.Messages");
    
    private final String id;

    public static int count() {
        return values().length;
    }

    private ItemType(String id) {
        this.id = id;
    }

    public static ItemType get(int index) {
        if (index >= 0 && index < count())
            return values()[index];
        else
            throw new IllegalArgumentException();
    }

    public String getId() {
        return this.id;
    }

    public String getVisibleName() {
        return res.getString(name() + ".name");
    }
}