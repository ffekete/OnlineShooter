package game.service;

import java.awt.geom.Point2D;

import game.config.constant.GameConfig;
import game.interfaces.Spawnable;
import game.util.RandomGenerator;

public class CoordinateHandler {

    public Point2D calculateItemCoordinates(Spawnable item, double speed, Point2D shipCoordinate) {
        Point2D coordinate;
        if (shipCoordinate == null) {
            double angle = item.getAngle() * Math.PI / 180.0d;
            coordinate = new Point2D.Double((double) item.getX() + speed * Math.cos(angle),
                    (double) item.getY() + speed * Math.sin(angle));
        } else {
            double randomX = shipCoordinate.getX() + RandomGenerator
                    .getRandomInRange(-1 * (GameConfig.MAX_ITEM_DROP_DISTANCE), GameConfig.MAX_ITEM_DROP_DISTANCE);
            double randomY = shipCoordinate.getY() + RandomGenerator
                    .getRandomInRange(-1 * (GameConfig.MAX_ITEM_DROP_DISTANCE), GameConfig.MAX_ITEM_DROP_DISTANCE);
            coordinate = new Point2D.Double(randomX, randomY);
        }

        if (coordinate.getX() > GameConfig.STAGE_POS_LIMIT_X) {
            coordinate.setLocation(GameConfig.STAGE_NEG_LIMIT_X + (coordinate.getX() - GameConfig.STAGE_POS_LIMIT_X),
                    coordinate.getY());
        }

        if (coordinate.getX() < GameConfig.STAGE_NEG_LIMIT_X) {
            coordinate.setLocation(GameConfig.STAGE_POS_LIMIT_X + (coordinate.getX() + GameConfig.STAGE_POS_LIMIT_X),
                    coordinate.getY());
        }

        if (coordinate.getY() > GameConfig.STAGE_POS_LIMIT_Y) {
            coordinate.setLocation(coordinate.getX(),
                    GameConfig.STAGE_NEG_LIMIT_Y + (coordinate.getY() - GameConfig.STAGE_POS_LIMIT_Y));
        }

        if (coordinate.getY() < GameConfig.STAGE_NEG_LIMIT_Y) {
            coordinate.setLocation(coordinate.getX(),
                    GameConfig.STAGE_POS_LIMIT_Y + (coordinate.getY() + GameConfig.STAGE_POS_LIMIT_Y));
        }

        return coordinate;
    }
}
