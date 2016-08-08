package game.config;

public class GameConfig {
	public final static int ITEM_SPAWNING_RATE = 800;
	public final static double BULLET_INITIAL_SPEED = 12.0d;
	public final static long BULLET_MAX_AGE = 150L;
	public final static long LASER_MAX_AGE = 5L;

	public final static long MAX_ITEMS_ON_STAGE = 20L;
	public final static long INVULN_CTR_MAX_VALUE = 200L;
	
	public final static long STAGE_NEG_LIMIT_X = -2000L;
	public final static long STAGE_NEG_LIMIT_Y = -2000L;
	public final static long STAGE_POS_LIMIT_X = 2000L;
	public final static long STAGE_POS_LIMIT_Y = 2000L;
	
	public final static long PLAYER_SCORE_VALUE = 100l;
	public final static long ITEM_SCORE_VALUE = 10l;
	
	public final static long PLAYER_RESPAWN_TIME = 300l;
	
	public static final boolean FREE_SYSTEM_TIME_MEASUREMENT_ENABLED = false;
}
