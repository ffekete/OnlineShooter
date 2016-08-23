package factory;

import java.awt.geom.Point2D;

import org.springframework.stereotype.Component;

import game.datatype.bullet.BulletData;
import game.interfaces.Bullet;

@Component
public class BulletBuilder {
    private Point2D coordinate = null;
    private Double angle = null;
    private Long playerId = null;
    private Long damage = null;

    public BulletBuilder setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public BulletBuilder setAngle(Double angle) {
        this.angle = angle;
        return this;
    }

    public BulletBuilder setPlayerId(long playerId) {
        this.playerId = playerId;
        return this;
    }

    public BulletBuilder setDamage(long damage) {
        this.damage = damage;
        return this;
    }

    public Bullet build() {
        if (this.coordinate == null) {
            throw new NullPointerException("(E) coordinate cannot be null!");
        }

        if (this.angle == null) {
            throw new NullPointerException("(E) angle cannot be null!");
        }

        if (this.playerId == null) {
            throw new NullPointerException("(E) playerId cannot be null!");
        }

        if (this.damage == null) {
            throw new NullPointerException("(E) damage cannot be null!");
        }

        Bullet bullet = new BulletData();
        bullet.setCoordinate(this.coordinate);
        bullet.setAngle(angle);
        bullet.setPlayerId(playerId);
        bullet.setDamage(damage);

        return bullet;
    }
}
