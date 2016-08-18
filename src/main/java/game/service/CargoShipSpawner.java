package game.service;

import java.awt.geom.Point2D;
import java.util.Random;

import game.config.constants.GameConfig;
import game.interfaces.Spawnable;

public class CargoShipSpawner extends Spawner {

    private static double pickRandomAngle() {
        Random random = new Random();

        return (double) random.nextInt(360);
    }

    private static Point2D pickRandomCoordinateForItemOnGameSpace() {
        Random random = new Random();

        double px = GameConfig.STAGE_POS_LIMIT_X
                - random.nextInt((int) (GameConfig.STAGE_POS_LIMIT_X - GameConfig.STAGE_NEG_LIMIT_X));
        double py = GameConfig.STAGE_POS_LIMIT_Y
                - random.nextInt((int) (GameConfig.STAGE_POS_LIMIT_Y - GameConfig.STAGE_NEG_LIMIT_Y));

        return new Point2D.Double(px, py);
    }

    public static void spawn(Spawnable item) {
        item.setLocation(pickRandomCoordinateForItemOnGameSpace());
        item.setAngle(pickRandomAngle());
    }
}
