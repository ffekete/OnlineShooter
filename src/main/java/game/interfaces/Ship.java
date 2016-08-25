package game.interfaces;

import java.awt.geom.Point2D;
import java.util.List;

public interface Ship extends Spawnable {
    public void initWeapons();

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

    public long getMaxHp();

    public void setMaxHp(long maxHp);

    public Weapon getWeapon();

    public void setWeapon(Weapon weapon);

    public List<Weapon> getWeapons();

    public void setWeapons(List<Weapon> weapons);

    public void addWeapon(Weapon weapon);

    public void selectWeapon(int index);

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

    public List<SpawnableItem> getCarriage();

    public void setCarriage(List<SpawnableItem> carriage);

    public void addItemToCargo(SpawnableItem item);

    public void resetCarriage();
}