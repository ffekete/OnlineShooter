package game.service;

import java.awt.geom.Point2D;
import java.util.Random;

import game.config.constant.CanvasConstants;
import game.util.RandomGenerator;

public class AISpawner {
    public static Point2D generateRandomCoordinate() {
        double randomX = RandomGenerator.getRandomInRange(minW, CanvasConstants.CANVAS_HALF_WIDTH);
        double randomY = RandomGenerator.getRandomInRange(minH, CanvasConstants.CANVAS_HALF_HEIGHT);

        return new Point2D.Double(randomX, randomY);
    }
}
