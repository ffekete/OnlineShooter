package game.service;

import java.awt.geom.Point2D;
import java.util.Random;

import game.config.constant.CanvasConstants;

public class AISpawner {
    public static Point2D generateRandomCoordinate() {
        Random rand = new Random();
        double randomX = CanvasConstants.CANVAS_WIDTH * rand.nextDouble();
        double randomY = CanvasConstants.CANVAS_HEIGHT * rand.nextDouble();

        return new Point2D.Double(randomX, randomY);
    }
}
