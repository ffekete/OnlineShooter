package game.datatypes.bullet;

import java.awt.geom.Point2D;

public class HeatSeekingMissile extends BulletData {

    public HeatSeekingMissile(double x, double y, double angle, long playerId, long damage) {
        super(new Point2D.Double(x, y), angle, playerId, damage);
    }
}