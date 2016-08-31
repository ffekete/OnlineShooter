package game.config.constant;

public class CanvasConstants {
    
    public static final int CANVAS_WIDTH = 1024;
    public static final int CANVAS_HEIGHT = 768;
    
    public static final long CANVAS_HALF_WIDTH = CANVAS_WIDTH / 2;
    public static final long CANVAS_HALF_HEIGHT = CANVAS_HEIGHT / 2;
    
    public static final double CANVAS_MAX_DISTANCE_FROM_MIDPOINT = Math.sqrt(CANVAS_HALF_WIDTH * CANVAS_HALF_WIDTH + CANVAS_HALF_HEIGHT*CANVAS_HALF_HEIGHT);
}
