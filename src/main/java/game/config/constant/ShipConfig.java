package game.config.constant;

public class ShipConfig {
    public final static String SHIP_TYPE_CARGOSHIP = "Cargoship";
    public final static String SHIP_TYPE_DELTAWING = "Deltawing";
    public final static String SHIP_TYPE_INTERCEPTOR = "Interceptor";
    public final static String SHIP_TYPE_MERCURY = "Mercury";
    public final static String SHIP_TYPE_QUICKSILVER = "Quicksilver";
    public final static String SHIP_TYPE_ASTEROID = "Asteroid";

    public final static long QUICKSILVER_MAX_HP = 15L;
    public final static double QUICKSILVER_INIT_SPEED = 6.0D;
    public final static double QUICKSILVER_INIT_MANEUVERABILITY = 10.0D;
    public final static ItemType QUICKSILVER_INIT_WEAPON = ItemType.SHOTGUN;

    public final static long MERCURY_MAX_HP = 25L;
    public final static double MERCURY_INIT_SPEED = 5.0D;
    public final static double MERCURY_INIT_MANEUVERABILITY = 12.0D;
    public final static ItemType MERCURY_INIT_WEAPON = ItemType.GATLING_GUN;

    public final static long INTERCEPTOR_MAX_HP = 20L;
    public final static double INTERCEPTOR_INIT_SPEED = 7.0D;
    public final static double INTERCEPTOR_INIT_MANEUVERABILITY = 14.0D;
    public final static ItemType INTERCEPTOR_INIT_WEAPON = ItemType.LASER_CANNON;

    public final static long DELTAWING_MAX_HP = 11L;
    public final static double DELTAWING_INIT_SPEED = 9.0D;
    public final static double DELTAWING_INIT_MANEUVERABILITY = 16.0D;
    public final static ItemType DELTAWING_INIT_WEAPON = ItemType.DOUBLE_GATLING_GUN;

    public final static long CARGO_SHIP_MAX_HP = 40L;
    public final static double CARGO_SHIP_INIT_SPEED = 3.0D;
    public final static double CARGO_SHIP_INIT_MANEUVERABILITY = 16.0D;
    public final static ItemType CARGO_SHIP_INIT_WEAPON = ItemType.NO_WEAPON;
    public final static int CARGO_SHIP_CARGO_CAPACITY = 10;

    public final static long ASTEROID_MAX_HP = 100L;
    public final static double ASTEROID_INIT_MIN_SPEED = 3.0D;
    public final static double ASTEROID_INIT_MAX_SPEED = 7.0D;
    public final static double ASTEROID_MANEUVERABILITY = 1.0D;
    public final static ItemType ASTEROID_INIT_WEAPON = ItemType.GATLING_GUN;
}
