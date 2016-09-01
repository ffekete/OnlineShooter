package game.datatype.ammo;

import java.awt.geom.Point2D;

public abstract class Energy extends AmmoParent {
	private double hitRadius;
	private double length;
	private Point2D startPoint;
    private Point2D endPoint;
    
    public Energy() {
    	startPoint = new Point2D.Double();
    	endPoint = new Point2D.Double();
    }
    
    public double getHitRadius() {
		return hitRadius;
	}

	public void setHitRadius(double hitRadius) {
		this.hitRadius = hitRadius;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
    
    public Point2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point2D endPoint) {
        this.endPoint = endPoint;
    }
}
