package game.datatype.ammo;

import java.awt.geom.Point2D;

import game.config.constant.AmmoType;
import game.controller.EventSender;
import game.interfaces.Ammo;
import game.interfaces.Spawnable;

public abstract class AmmoParent implements Ammo {
	private long playerId;
	private AmmoType type;
    private Point2D coordinate;
    private double angle;
    private double speed;
    private double age;
    private double damage;
    private boolean alreadyHit;
    
    public AmmoParent() {
    	this.age = 0L;
    	this.alreadyHit = false;
    }

    @Override
    public AmmoType getType() {
		return this.type;
	}

    @Override
	public void setType(AmmoType type) {
		this.type = type;
	}

	@Override
    public abstract boolean isAgeCounterExpired();

    @Override
    public double getAngle() {
        return this.angle;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
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
    public long getPlayerId() {
        return playerId;
    }

    @Override
    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }
    
    @Override
    public void increaseDamage(double damage) {
        this.damage += damage;
    }

    @Override
    public void increaseAge() {
        this.age++;
    }

    @Override
    public double getAge() {
        return this.age;
    }

    @Override
    public void setAge(double age) {
        this.age = age;
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
    public void effect() {
        // empty
    }

    @Override
    public boolean hits(Spawnable item) {
        return (Math.abs(this.coordinate.getX() - item.getX()) < item.getHitRadius())
                && (Math.abs(this.coordinate.getY() - item.getY())) < item.getHitRadius();
    }

    @Override
    public void hitDetected() {
        // empty
    }

    @Override
    public void hitDetected(Spawnable item) {
    }
    
    @Override
	public double getHitRadius() {
		return 0;
	}

	@Override
	public void setHitRadius(double hitRadius) {
	}

    @Override
    public void hitDetected(Spawnable item, EventSender eventSender) {
        eventSender.sendItemHitNotification(item);
    }

    @Override
    public void setCoordinate(double x, double y) {
        this.coordinate.setLocation(x, y);
    }

    @Override
    public void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public Point2D getCoordinate() {
        return this.coordinate;
    }
    
    public boolean isAlreadyHit() {
		return alreadyHit;
	}

	public void setAlreadyHit(boolean alreadyHit) {
		this.alreadyHit = alreadyHit;
	}
}
