package model;

public class BulletData {
	private double x;
	private double y;
	private double angle;
	private long age; 
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public BulletData(double x, double y, double angle) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.age = 0L;
	}
	
	public void increaseAge(){
		this.age++;
	}
	
	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
