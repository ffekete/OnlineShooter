package game.config.constant;

public enum ItemType {
    // Weapons
    GATLING_GUN("gatlingGun", "Gatling Gun"),
    LASER_CANNON("laserCannon", "Laser Cannon"),
    DOUBLE_GATLING_GUN("doubleGatlingGun", "Double Gatling Gun"),
    SHOTGUN("shotgun", "Shotgun"),
    MISSILE_LAUNCHER("missileLauncher", "Missile Launcher"),

    // Power-ups
    INCREASE_DAMAGE("increaseDamage", "Damage++"),
    INCRASE_RATE_OF_FIRE("increaseRof","Rate Of Fire++"),
    INCREASE_MANEUVERABILITY("increaseManeuverability","Maneuverability++"),
    INCREASE_SPEED("increaseSpeed", "Speed++"),

    // Shields
    NORMAL_SHIELD("normalShield", "Normal Shield"),
    ATOM_SHIELD("atomShield","Atom Shield"),
    PLASMA_SHIELD("plasmaShield", "Plasma Shield"),
    
    // Other
    HEALTH_PACK("healthPack", "Health Pack +5"),
    INCREASE_SCORE("increaseScore", "Score++"),
    
    NO_SHIELD("noShield", ""),
    NO_WEAPON("noWeapon", "");
    
    private final String id;
    private final String visibleName;

    public static int count() {
        return values().length;
    }

    private ItemType(String id, String visibleName) {
        this.id = id;
        this.visibleName = visibleName;
    }

    public static ItemType get(int index) {
        if (index >= 0 && index < count())
            return values()[index];
        else
            throw new IllegalArgumentException();
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getVisibleName() {
        return this.visibleName;
    }
}