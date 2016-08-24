package game.datatype.bullet;

import java.awt.geom.Point2D;

public class HeatSeekingMissile extends BulletData {

    public HeatSeekingMissile(Point2D coordinate, double angle, long playerId, long damage) {
        super.setCoordinate(coordinate);
        super.setAngle(angle);
        super.setPlayerId(playerId);
        super.setDamage(damage);
    }
}