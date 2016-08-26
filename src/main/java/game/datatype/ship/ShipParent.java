package game.datatype.ship;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.config.constant.AmmoType;
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
	private Map<AmmoType, Long> ammoCount;
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
	public double decreaseHp(double value) {
		double shieldProtection = getShield().getProtection();

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
	public void initWeaponsAndAmmo() {
		this.weapons = new ArrayList<Weapon>();
		this.ammoCount = new HashMap<AmmoType, Long>(AmmoType.count());
		for(AmmoType ammoType : AmmoType.values()) {
			this.ammoCount.put(ammoType, 0L);
		}
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
		this.addAmmoCount(weapon.getAmmoType(), weapon.getInitAmmoCount());
		for (Weapon w : this.weapons) {
			if(w.getAmmoType() == weapon.getAmmoType()) {
				if (w.getType() != weapon.getType()) {
					if(weapon.getPower() > w.getPower()) {
						this.weapons.set(this.weapons.indexOf(w), weapon);
						if(w.equals(this.weapon)) {
							this.weapon = weapon;
						}
					}
				}
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
	public Map<AmmoType, Long> getAmmoCount() {
		return ammoCount;
	}

	@Override
	public void setAmmoCount(Map<AmmoType, Long> ammo) {
		this.ammoCount = ammo;
	}

	@Override
	public boolean canShoot() {
		return this.hasAmmoLeft() && this.weapon.isReadyToShoot();
	}
	
	@Override
	public long getActualAmmoCount() {
		return ammoCount.get(this.weapon.getAmmoType());
	}
	
	@Override
	public long getAmmoCount(AmmoType ammoType) {
		return ammoCount.get(ammoType);
	}
	
	@Override
	public void addActualAmmoCount(long ammoAdd) {
		long count = ammoCount.get(this.weapon.getAmmoType());
		ammoCount.put(weapon.getAmmoType(), count + ammoAdd);
	}

	@Override
	public void addAmmoCount(AmmoType ammoType, long ammoAdd) {
		long count = ammoCount.get(ammoType);
		ammoCount.put(ammoType, count + ammoAdd);
	}

	@Override
	public boolean hasAmmoLeft() {
		return ammoCount.get(this.weapon.getAmmoType()) >= this.weapon.getShotCount();
	}

	@Override
	public void decreaseAmmoCount() {
		long count = ammoCount.get(this.weapon.getAmmoType());
		if (count >= this.weapon.getShotCount()) {
			count -= this.weapon.getShotCount();
			ammoCount.put(weapon.getAmmoType(), count);
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

	@Override
	public double getManeuverability() {
		return maneuverability;
	}

	@Override
	public void setManeuverability(double maneuverability) {
		this.maneuverability = maneuverability;
	}

	@Override
	public List<SpawnableItem> getCarriage() {
		return this.carriage;
	}

	@Override
	public void setCarriage(List<SpawnableItem> carriage) {
		this.carriage = carriage;
	}

	@Override
	public int getMaxCargoCapacity() {
		return this.maxCargoCapacity;
	}

	@Override
	public void setMaxCargoCapacity(int maxCargoCapacity) {
		this.maxCargoCapacity = maxCargoCapacity;
	}

	@Override
	public void addItemToCargo(SpawnableItem item) {
		if (this.carriage.size() < this.maxCargoCapacity) {
			this.carriage.add(item);
		}
	}
}
