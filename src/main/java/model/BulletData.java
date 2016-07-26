package model;

public class BulletData {
	private long x;
	private long y;
	private double angle;
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public BulletData(long x, long y, double angle) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public long getX() {
		return x;
	}
	
	public void setX(long x) {
		this.x = x;
	}
	
	public long getY() {
		return y;
	}
	
	public void setY(long y) {
		this.y = y;
	}
	
	
}
