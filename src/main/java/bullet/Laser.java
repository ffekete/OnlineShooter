package bullet;

import java.awt.geom.Line2D;

import interfaces.Spawnable;
import model.Coordinate;

public class Laser extends BulletData{
	public Coordinate startPoint;
	public Coordinate endPoint;
	
	public Laser(double x, double y, double angle, long playerId, long damage) {
		super(x, y, angle, playerId, damage);
	}
	
	public boolean hits(Spawnable item){
		double distance = Line2D.ptSegDist(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(), item.getX(), item.getY());
		
		return distance == 0.0d;
	}
}
