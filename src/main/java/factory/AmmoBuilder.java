package factory;

import java.awt.geom.Point2D;

import org.springframework.stereotype.Component;

import game.config.constant.AmmoType;
import game.datatype.ammo.Beam;
import game.datatype.ammo.Bullet;
import game.datatype.ammo.Canister;
import game.datatype.ammo.HeatSeekingMissile;
import game.datatype.ammo.Missile;
import game.interfaces.Ammo;

@Component
public class AmmoBuilder {
    private Point2D coordinate = null;
    private Double angle = null;
    private Long damageBonus = null;
    private Long playerId = null;

    public AmmoBuilder setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public AmmoBuilder setAngle(double angle) {
        this.angle = angle;
        return this;
    }

    public AmmoBuilder setDamageBonus(long damageBonus) {
        this.damageBonus = damageBonus;
        return this;
    }

    public AmmoBuilder setPlayerId(long playerId) {
        this.playerId = playerId;
        return this;
    }

    public Ammo build(AmmoType type) {
        if (this.coordinate == null) {
            throw new NullPointerException("(E) coordinate cannot be null!");
        }

        if (this.angle == null) {
            throw new NullPointerException("(E) angle cannot be null!");
        }

        if (this.damageBonus == null) {
            throw new NullPointerException("(E) damage bonus cannot be null!");
        }

        if (this.playerId == null) {
            throw new NullPointerException("(E) playerId cannot be null!");
        }

        Ammo ammo;

        switch (type) {
        case BULLET:
            ammo = new Bullet();
            ammo.setDamage(AmmoType.BULLET.getDamage(damageBonus));
            break;
        case CANISTER:
            ammo = new Canister();
            ammo.setDamage(AmmoType.CANISTER.getDamage(damageBonus));
            break;
        case LASER_BEAM:
            ammo = new Beam(this.coordinate, this.angle, this.playerId, 0);
            ammo.setDamage(AmmoType.LASER_BEAM.getDamage(damageBonus));
            break;
        case MISSILE:
            ammo = new Missile();
            ammo.setDamage(AmmoType.MISSILE.getDamage(damageBonus));
            break;
        case HEAT_SEEKING_MISSILE:
            ammo = new HeatSeekingMissile();
            ammo.setDamage(AmmoType.HEAT_SEEKING_MISSILE.getDamage(damageBonus));
            break;
        default:
            throw new RuntimeException("Unknownw ammo type!");
        }

        ammo.setCoordinate(this.coordinate);
        ammo.setAngle(this.angle);
        ammo.setPlayerId(this.playerId);

        return ammo;
    }
}
