package interfaces;

public interface Bullet {
	public double getAngle();

	public void setAngle(double angle);

	public long getPlayerId();

	public void setPlayerId(long playerId);

	public long getDamage();
	
	public void setDamage(long damage);

	public void increaseAge();
	
	public long getAge();

	public void setAge(long age);

	public double getX();
	
	public void setX(double x);
	
	public double getY();
	
	public void setY(double y);
	
	/** Periodically updated bullet effect. Use this to create heat-seeking missiles, etc.. */
	public void effect();
	
	public void hitDetected();
	
	public boolean hits(Spawnable item);
}
