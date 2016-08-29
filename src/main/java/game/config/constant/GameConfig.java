package game.config.constant;

public class GameConfig {
    public final static int ITEM_SPAWNING_RATE = 800;
    

    public final static long MAX_ITEMS_ON_STAGE = 20L;
    public final static long INVULN_CTR_MAX_VALUE = 200L;

    public final static long STAGE_NEG_LIMIT_X = -2048L;
    public final static long STAGE_NEG_LIMIT_Y = -2048L;
    public final static long STAGE_POS_LIMIT_X = 2048L;
    public final static long STAGE_POS_LIMIT_Y = 2048L;

    public final static long PLAYER_SCORE_VALUE = 100l;
    public final static long AI_SCORE_VALUE = 15l;
    public final static long ITEM_SCORE_VALUE = 10l;
    public final static long ASTEROID_SCORE_VALUE = 10l;

    public final static long PLAYER_RESPAWN_TIME = 300l;

    public static final boolean FREE_SYSTEM_TIME_MEASUREMENT_ENABLED = false;

    public static final double MOUSE_SPEED_SENSITIVITY_PERCENT = 0.75d;

    public static final int MAX_ITEM_DROP_DISTANCE = 100;
}
