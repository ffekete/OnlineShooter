package game.interfaces;

import java.awt.geom.Point2D;

public interface Spawnable {
    public void setCoordinate(double x, double y);

    public void setCoordinate(Point2D coordinate);

    public Point2D getCoordinate();

    public void setAngle(double angle);

    public double getX();

    public double getY();

    public double getAngle();
    
    double getHitRadius();

	void setHitRadius(double hitRadius);
}