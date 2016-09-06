package game.service;

import java.awt.geom.Point2D;

import game.config.constant.CanvasConstants;
import game.util.RandomGenerator;

public class AISpawner {
    public static Point2D generateRandomCoordinate() {
        double randomX = RandomGenerator.getRandomInRange(0, CanvasConstants.CANVAS_WIDTH);
        double randomY = RandomGenerator.getRandomInRange(0, CanvasConstants.CANVAS_HEIGHT);
        return new Point2D.Double(randomX, randomY);
    }
}
