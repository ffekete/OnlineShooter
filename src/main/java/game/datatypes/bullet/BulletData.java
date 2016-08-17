package game.datatypes.bullet;

import java.awt.geom.Point2D;

import game.config.GameConfig;
import game.controller.EventSender;
import game.interfaces.Bullet;
import game.interfaces.Spawnable;

public class BulletData implements Bullet {
    private double x;
    private double y;
    private Point2D coordinate;
    private double angle;
    private long age;
    private long playerId;
    private long damage;

    public boolean isAgeCounterExpired() {
        if (age >= GameConfig.BULLET_MAX_AGE) {
            age = 0;
            return true;
        }
        return false;
    }

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

    public BulletData(Point2D coordinate, double angle, long playerId, long damage) {
        super();
        this.coordinate = coordinate;
        this.angle = angle;
        this.age = 0L;
        this.playerId = playerId;
        this.damage = damage;
    }

    public void increaseAge() {
        this.age++;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public double getX() {
        return coordinate.getX();
    }

    public void setX(double x) {
        this.coordinate.setLocation(x, this.coordinate.getY());
    }

    public double getY() {
        return coordinate.getY();
    }

    public void setY(double y) {
        this.coordinate.setLocation(this.coordinate.getX(), y);
    }

    @Override
    public void effect() {
        // empty
    }

    @Override
    public boolean hits(Spawnable item) {
        return (Math.abs(this.coordinate.getX() - item.getX()) < 10.0d
                && (Math.abs(this.coordinate.getY() - item.getY())) < 10.0d);
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

    @Override
    public String getPhysicalRepresentation() {
        return new String(
                "{\"shape\": \"circle\", \"startx\": \"" + x + "\", \"starty\": \"" + y + "\", \"radius\" : \"15\"}");
    }
}
