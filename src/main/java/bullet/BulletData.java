package bullet;

import controller.EventSender;
import interfaces.Bullet;
import interfaces.Spawnable;

public class BulletData implements Bullet{
	private double x;
	private double y;
	private double angle;
	private long age;
	private long playerId;
	private long damage;
		
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

	public long getDamage() {
		return damage;
	}

	public void setDamage(long damage) {
		this.damage = damage;
	}

	public BulletData(double x, double y, double angle, long playerId, long damage) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.age = 0L;
		this.playerId = playerId;
		this.damage = damage;
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

	@Override
	public void effect() {
		// empty		
	}

	@Override
	public boolean hits(Spawnable item) {
		return (Math.abs(x - item.getX()) < 10.0d && (Math.abs(y - item.getY())) < 10.0d);
	}

	@Override
	public void hitDetected() {
		// empty		
	}

	@Override
	public void hitDetected(Spawnable item) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void hitDetected(Spawnable item, EventSender eventSender) {
		eventSender.sendItemHitNotification(item);
	}
}
