package game.interfaces;

import java.awt.geom.Point2D;

public interface Ship extends Spawnable{
    public void initWeapon();
    public long decreaseHp(long value);
    public String getColor();
    public void setColor(String color);
    public double getSpeed();
    public void setSpeed(double speed);
    public double getMaxSpeed();
    public void setMaxSpeed(double maxSpeed);
    public double getAngle();
    public void setAngle(double angle);
    public Point2D getCoordinate();
    public void setCoordinate(Point2D coordinate);
    public void setCoordinate(double x, double y);
    public long getHp();
    public void setHp(long hp);
    public Weapon getWeapon();
    public void setWeapon(Weapon weapon);
    public double getManeuverability();
    public void setManeuverability(double maneuverability);
    public void increaseManeuverablility(double value);
    public Shield getShield();
    public void setShield(Shield shield);
    public double getX();
    public void setX(double x);
    public double getY();
    public void setY(double y);
    public String getShipType();
    public void setShipType(String shipType);
    public void increaseShieldPower();
    public void increaseSpeed(double value);
    public void resetHp();
    public void resetManeuverability();
    public void resetSpeed();
}