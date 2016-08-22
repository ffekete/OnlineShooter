package game.service;

import java.awt.geom.Point2D;
import java.util.Random;

import game.config.constant.CanvasConstants;

public class AISpawner {
    public static Point2D generateRandomCoordinate() {
        double minW = -1 * (CanvasConstants.CANVAS_HALF_WIDTH);
        double minH = -1 * (CanvasConstants.CANVAS_HALF_HEIGHT);
        Random rand = new Random();
        double randomX = minW + (CanvasConstants.CANVAS_HALF_WIDTH - minW) * rand.nextDouble();
        double randomY = minH + (CanvasConstants.CANVAS_HALF_HEIGHT - minH) * rand.nextDouble();
        return new Point2D.Double(randomX, randomY);
    }
}
