package interfaces;

import model.Coordinate;

public interface Ship {
	public void initWeapon();
	public long decreaseHp(long value);
	public void increaseSpeed();
	public String getColor();
	public void setColor(String color);
	public double getSpeed();
	public void setSpeed(double speed);
	public double getMaxSpeed();
	public Double getShipAngle();
	public void setShipAngle(Double shipAngle);
	public Coordinate getCoordinate();
	public void setCoordinate(Coordinate coordinate);
	public Long getHp();
	public void setHp(Long hp);
	public Weapon getWeapon();
	public void setWeapon(Weapon weapon);
	public double getManeuverability();
	public void setManeuverability(double maneuverability);
	public void increaseManeuverablility(double value);
	public Shield getShield();
	public void setShield(Shield shield);
}
