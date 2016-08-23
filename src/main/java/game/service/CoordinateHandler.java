package game.service;

import java.awt.geom.Point2D;

import org.springframework.stereotype.Component;

import game.config.constant.GameConfig;
import game.interfaces.Spawnable;

@Component
public class CoordinateHandler {

    public Point2D calculateItemCoordinates(Spawnable item, double speed) {
        double angle = item.getAngle() * Math.PI / 180.0d;
        Point2D coordinate = new Point2D.Double((double) item.getX() + speed * Math.cos(angle),
                (double) item.getY() + speed * Math.sin(angle));

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
                    GameConfig.STAGE_POS_LIMIT_Y + (coordinate.getY() + GameConfig.STAGE_NEG_LIMIT_Y));
        }

        return coordinate;
    }
}
