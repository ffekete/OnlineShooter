package bullet;

import java.awt.geom.Line2D;

import config.WeaponConfig;
import interfaces.Spawnable;
import model.Coordinate;

public class LaserBeam extends BulletData{
	private Coordinate startPoint;
	private Coordinate endPoint;
	
	public Coordinate getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Coordinate startPoint) {
		this.startPoint = startPoint;
	}

	public Coordinate getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Coordinate endPoint) {
		this.endPoint = endPoint;
	}

	public LaserBeam(double x, double y, double angle, long playerId, long damage) {
		super(x, y, angle, playerId, damage);
		
		double resultx;
		double resulty;
		
		angle = angle * Math.PI / 180.0d;
		
		resultx = x + WeaponConfig.LASER_BEAM_LENGTH * Math.cos(angle);
		resulty = y + WeaponConfig.LASER_BEAM_LENGTH * Math.sin(angle);
		
		setStartPoint(new Coordinate(x, y));
		setEndPoint(new Coordinate(resultx, resulty));
	}
	
	public boolean hits(Spawnable item){
		double distance = Line2D.ptSegDist(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(), item.getX(), item.getY());
		return Math.abs(distance) < 50.0d;
	}
}
