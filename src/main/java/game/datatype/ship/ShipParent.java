package game.datatype.ship;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import factory.CarriageBuilder;
import game.interfaces.Shield;
import game.interfaces.Ship;
import game.interfaces.SpawnableItem;
import game.interfaces.Weapon;

public abstract class ShipParent implements Ship {
    private Point2D coordinate;
    private String color;
    private String shipType;
    private Shield shield;
    private long hp;
    private long maxHp;

    private Double angle;
    private Weapon weapon;
    private List<Weapon> weapons;
    private double speed;
    private double maxSpeed;
    private double maneuverability;
    private List<SpawnableItem> carriage;
    private int maxCargoCapacity = 0;

    public ShipParent() {
        this.coordinate = new Point2D.Double(0, 0);
        this.carriage = new ArrayList<SpawnableItem>();
    }

    @Override
    public void increaseManeuverablility(double value) {
        this.maneuverability -= value;
        if (this.maneuverability < 1.0d)
            this.maneuverability = 1.0d;
    }

    @Override
    public void setHp(long hp) {
        this.hp = hp;
    }

    @Override
    public long getMaxHp() {
        return maxHp;
    }

    @Override
    public void setMaxHp(long maxHp) {
        this.maxHp = maxHp;
    }

    @Override
    public void increaseSpeed(double value) {
        this.speed += value;
    }

    @Override
    public long decreaseHp(long value) {
        long shieldProtection = getShield().getProtection();

        if (shieldProtection > 0L) {
            getShield().decreaseProtection(value);
        } else {
            hp -= value;
        }
        return hp;
    }

    @Override
    public void increaseShieldPower() {
        getShield().increaseShieldPower();
    }

    @Override
    public void initWeapons() {
        this.weapons = new ArrayList<Weapon>();
    }

    @Override
    public double getX() {
        return coordinate.getX();
    }

    @Override
    public double getY() {
        return coordinate.getY();
    }

    @Override
    public void setX(double x) {
        this.setCoordinate(x, this.coordinate.getY());
    }

    @Override
    public void setY(double y) {
        this.setCoordinate(this.coordinate.getX(), y);
    }

    @Override
    public void setCoordinate(double x, double y) {
        this.coordinate.setLocation(x, y);
    }

    @Override
    public void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    public abstract void resetHp();

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getShipType() {
        return shipType;
    }

    @Override
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    @Override
    public Shield getShield() {
        return shield;
    }

    @Override
    public void setShield(Shield shield) {
        this.shield = shield;
    }

    @Override
    public long getHp() {
        return hp;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public void addWeapon(Weapon weapon) {
        for (Weapon w : this.weapons) {
            if (w.getType() == weapon.getType()) {
                w.addAmmo(weapon.getAmmo());
                return;
            }
        }
        this.weapons.add(weapon);
    }

    @Override
    public void selectWeapon(int index) {
        if (index >= 0 && index < this.weapons.size()) {
            this.weapon = this.weapons.get(index);
        }
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public Point2D getCoordinate() {
        return coordinate;
    }

    public double getManeuverability() {
        return maneuverability;
    }

    public void setManeuverability(double maneuverability) {
        this.maneuverability = maneuverability;
    }

    public List<SpawnableItem> getCarriage() {
        return this.carriage;
    }

    public void setCarriage(List<SpawnableItem> carriage) {
        this.carriage = carriage;
    }

    public int getMaxCargoCapacity() {
        return this.maxCargoCapacity;
    }

    public void setMaxCargoCapacity(int maxCargoCapacity) {
        this.maxCargoCapacity = maxCargoCapacity;
    }

    public void addItemToCargo(SpawnableItem item) {
        if (this.carriage.size() < this.maxCargoCapacity) {
            this.carriage.add(item);
        }
    }

    @Override
    public void resetCarriage() {
        this.setCarriage(CarriageBuilder.buildHalfCargo(this.maxCargoCapacity));
    }
}
