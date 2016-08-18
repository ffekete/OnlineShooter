package game.service;

import java.awt.geom.Point2D;

import org.springframework.stereotype.Component;

import game.config.constants.GameConfig;
import game.interfaces.Spawnable;

@Component
public class CoordinateHandler {

    public Point2D calculateItemCoordinates(Spawnable item, double speed) {
        double resultX;
        double resultY;

        double angle = item.getAngle() * Math.PI / 180.0d;

        resultX = (double) item.getX() + speed * Math.cos(angle);
        resultY = (double) item.getY() + speed * Math.sin(angle);

        if (resultX > GameConfig.STAGE_POS_LIMIT_X)
            resultX = GameConfig.STAGE_NEG_LIMIT_X + (resultX - GameConfig.STAGE_POS_LIMIT_X);
        if (resultX < GameConfig.STAGE_NEG_LIMIT_X)
            resultX = GameConfig.STAGE_POS_LIMIT_X + (resultX + GameConfig.STAGE_POS_LIMIT_X);

        if (resultY > GameConfig.STAGE_POS_LIMIT_Y)
            resultY = GameConfig.STAGE_NEG_LIMIT_Y + (resultY - GameConfig.STAGE_POS_LIMIT_Y);
        if (resultY < GameConfig.STAGE_NEG_LIMIT_Y)
            resultY = GameConfig.STAGE_POS_LIMIT_Y + (resultY + GameConfig.STAGE_NEG_LIMIT_Y);

        return new Point2D.Double(resultX, resultY);
    }
}
