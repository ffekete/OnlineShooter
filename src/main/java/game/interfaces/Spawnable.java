package game.interfaces;

import java.awt.geom.Point2D;

public interface Spawnable {
    public void setLocation(double x, double y);

    public void setLocation(Point2D coordinate);

    public void setAngle(double angle);

    public double getX();

    public double getY();

    public double getAngle();
}