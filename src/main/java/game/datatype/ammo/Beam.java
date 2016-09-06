package game.datatype.ammo;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import game.config.constant.AmmoType;
import game.datatype.ammo.type.Energy;
import game.interfaces.Spawnable;

public class Beam extends Energy {

    public Beam(Point2D coordinate, double angle, long playerId, long damage) {
        super.setType(AmmoType.LASER_BEAM);
        super.setCoordinate(coordinate);
        super.setAngle(angle);
        super.setSpeed(AmmoType.LASER_BEAM.getInitSpeed());
        super.setPlayerId(playerId);
        super.setDamage(damage);

        double resultX;
        double resultY;

        angle = angle * Math.PI / 180.0d;

        resultX = coordinate.getX() + AmmoType.LASER_BEAM.getLength() * Math.cos(angle);
        resultY = coordinate.getY() + AmmoType.LASER_BEAM.getLength() * Math.sin(angle);

        setStartPoint(coordinate);
        setEndPoint(new Point2D.Double(resultX, resultY));
    }

    @Override
    public boolean isAgeCounterExpired() {
        if (this.getAge() >= AmmoType.LASER_BEAM.getInitAge()) {
            this.setAge(0);
            ;
            return true;
        }
        return false;
    }

    public boolean hits(Spawnable item) {
        double distance = Line2D.ptSegDist(getStartPoint().getX(), getStartPoint().getY(), getEndPoint().getX(),
                getEndPoint().getY(), item.getX(), item.getY());
        return Math.abs(distance) <= (AmmoType.LASER_BEAM.getInitHitRadius() + item.getHitRadius());
    }
    
    @Override
    public void updateSpeed() {
        this.setSpeed(this.getSpeed() + AmmoType.LASER_BEAM.getAcceleration());
        if (this.getSpeed() > AmmoType.LASER_BEAM.getMaxSpeed()) {
            this.setSpeed(AmmoType.LASER_BEAM.getMaxSpeed());
        }
    }
    
    @Override
    public String getPhysicalRepresentation() {
        return "{\"shape\" : \"line\", \"startx\": \"" + getStartPoint().getX() + "\", \"starty\" :\""
                + getStartPoint().getY() + "\", \"endx\": \"" + getEndPoint().getX() + "\", \"endy\": \""
                + getEndPoint().getY() + "\"}";
    }
}
