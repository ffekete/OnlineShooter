package model;

public class BulletData {
	private double x;
	private double y;
	private double angle;
	private long age;
	private long playerId;
		
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public BulletData(double x, double y, double angle, long playerId) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.age = 0L;
		this.playerId = playerId;
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
