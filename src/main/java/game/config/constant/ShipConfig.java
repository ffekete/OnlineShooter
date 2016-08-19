package game.config.constants;

import game.config.WeaponId;

public class ShipConfig {
    public final static String   SHIP_TYPE_CARGOSHIP = "Cargoship";
    public final static String   SHIP_TYPE_DELTAWING = "Deltawing";
    public final static String   SHIP_TYPE_INTERCEPTOR = "Interceptor";
    public final static String   SHIP_TYPE_MERCURY = "Mercury";
    public final static String   SHIP_TYPE_QUICKSILVER = "Quicksilver";
    
    public final static long     QUICKSILVER_INIT_HP = 15L;
    public final static double   QUICKSILVER_INIT_SPEED = 6.0D;
    public final static double   QUICKSILVER_INIT_MANEUVERABILITY = 10.0D;
    public final static WeaponId QUICKSILVER_INIT_WEAPON = WeaponId.SHOTGUN;
    
    public final static long     MERCURY_INIT_HP = 25L;
    public final static double   MERCURY_INIT_SPEED = 5.0D;
    public final static double   MERCURY_INIT_MANEUVERABILITY = 12.0D;
    public final static WeaponId MERCURY_INIT_WEAPON = WeaponId.MACHINEGUN;
    
    public final static long     INTERCEPTOR_INIT_HP = 20L;
    public final static double   INTERCEPTOR_INIT_SPEED = 7.0D;
    public final static double   INTERCEPTOR_INIT_MANEUVERABILITY = 14.0D;
    public final static WeaponId INTERCEPTOR_INIT_WEAPON = WeaponId.LASER_CANNON;
    
    public final static long     DELTAWING_INIT_HP = 11L;
    public final static double   DELTAWING_INIT_SPEED = 9.0D;
    public final static double   DELTAWING_INIT_MANEUVERABILITY = 16.0D;
    public final static WeaponId DELTAWING_INIT_WEAPON = WeaponId.DOUBLE_GATLING;
    
    public final static long     CARGO_SHIP_INIT_HP = 40L;
    public final static double   CARGO_SHIP_INIT_SPEED = 9.0D;
    public final static double   CARGO_SHIP_INIT_MANEUVERABILITY = 16.0D;
    public final static WeaponId CARGO_SHIP_INIT_WEAPON = null;
    public final static int      CARGO_SHIP_MAX_CAPACITY_DURING_CREATION = 5;
    
}
