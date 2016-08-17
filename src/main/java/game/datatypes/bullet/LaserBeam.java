package game.datatypes.bullet;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import game.config.GameConfig;
import game.config.WeaponConfig;
import game.datatypes.Coordinate;
import game.interfaces.Spawnable;

public class LaserBeam extends BulletData {
    private Point2D startPoint;
    private Point2D endPoint;

    public Point2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    public boolean isAgeCounterExpired() {
        if (super.getAge() >= GameConfig.LASER_MAX_AGE) {
            super.setAge(0);
            return true;
        }
        return false;
    }

    public void setEndPoint(Point2D endPoint) {
        this.endPoint = endPoint;
    }

    public LaserBeam(Point2D coordinate, double angle, long playerId, long damage) {
        super(coordinate, angle, playerId, damage);

        double resultX;
        double resultY;

        angle = angle * Math.PI / 180.0d;

        resultX = coordinate.getX() + WeaponConfig.LASER_BEAM_LENGTH * Math.cos(angle);
        resultY = coordinate.getY() + WeaponConfig.LASER_BEAM_LENGTH * Math.sin(angle);

        setStartPoint(coordinate);
        setEndPoint(new Point2D.Double(resultX, resultY));
    }

    public boolean hits(Spawnable item) {
        double distance = Line2D.ptSegDist(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(),
                item.getX(), item.getY());
        return Math.abs(distance) <= WeaponConfig.LASER_BEAM_HIT_RADIUS;
    }

    @Override
    public String getPhysicalRepresentation() {
        return "{\"shape\" : \"line\", \"startx\": \"" + startPoint.getX() + "\", \"starty\" :\"" + startPoint.getY()
                + "\", \"endx\": \"" + endPoint.getX() + "\", \"endy\": \"" + endPoint.getY() + "\"}";
    }
}
