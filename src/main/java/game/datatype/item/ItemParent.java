package game.datatypes.Items;

import java.awt.geom.Point2D;

import game.interfaces.SpawnableItem;

public abstract class ItemParent implements SpawnableItem {
    private String name;
    private Point2D coordinate = new Point2D.Double(0, 0);
    private double angle;

    @Override
    public void setLocation(double x, double y) {
        this.coordinate.setLocation(x, y);
    }

    @Override
    public void setLocation(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public double getX() {
        return this.coordinate.getX();
    }

    @Override
    public double getY() {
        return this.coordinate.getY();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return this.angle;
    }
}
