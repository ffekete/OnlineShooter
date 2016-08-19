package game.datatypes.bullet;

import java.awt.geom.Point2D;

import game.config.constants.GameConfig;
import game.controller.EventSender;
import game.interfaces.Bullet;
import game.interfaces.Spawnable;

public class BulletData implements Bullet {
    private Point2D coordinate;
    private double angle;
    private long age = 0L;
    private long playerId;
    private long damage;

    @Override
    public boolean isAgeCounterExpired() {
        if (age >= GameConfig.BULLET_MAX_AGE) {
            age = 0;
            return true;
        }
        return false;
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
    public long getPlayerId() {
        return playerId;
    }

    @Override
    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public long getDamage() {
        return damage;
    }

    @Override
    public void setDamage(long damage) {
        this.damage = damage;
    }

    @Override
    public void increaseAge() {
        this.age++;
    }

    @Override
    public long getAge() {
        return age;
    }

    @Override
    public void setAge(long age) {
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
                "{\"shape\": \"circle\", \"startx\": \"" + this.coordinate.getX() + "\", \"starty\": \"" + this.coordinate.getY() + "\", \"radius\" : \"15\"}");
    }

    @Override
    public void setLocation(double x, double y) {
        this.coordinate.setLocation(x, y);
    }
    
    @Override
    public void setLocation(Point2D coordinate) {
        this.coordinate = coordinate;
    }
}
