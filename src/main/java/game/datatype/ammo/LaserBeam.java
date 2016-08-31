package game.datatype.ammo;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import game.config.constant.AmmoConfig;
import game.config.constant.AmmoType;
import game.interfaces.Spawnable;

public class LaserBeam extends Energy {

    public LaserBeam(Point2D coordinate, double angle, long playerId, long damage) {
    	super.setType(AmmoType.LASER_BEAM);
        super.setCoordinate(coordinate);
        super.setAngle(angle);
        super.setSpeed(AmmoConfig.LASER_BEAM_INIT_SPEED);
        super.setPlayerId(playerId);
        super.setDamage(damage);

        double resultX;
        double resultY;

        angle = angle * Math.PI / 180.0d;

        resultX = coordinate.getX() + AmmoConfig.LASER_BEAM_INIT_LENGTH * Math.cos(angle);
        resultY = coordinate.getY() + AmmoConfig.LASER_BEAM_INIT_LENGTH * Math.sin(angle);

        setStartPoint(coordinate);
        setEndPoint(new Point2D.Double(resultX, resultY));
    }
    
    @Override
    public boolean isAgeCounterExpired() {
        if (this.getAge() >= AmmoConfig.LASER_BEAM_INIT_AGE) {
            this.setAge(0);;
            return true;
        }
        return false;
    }

    public boolean hits(Spawnable item) {
        double distance = Line2D.ptSegDist(getStartPoint().getX(), getStartPoint().getY(), getEndPoint().getX(), getEndPoint().getY(),
                item.getX(), item.getY());
        return Math.abs(distance) <= (AmmoConfig.LASER_BEAM_INIT_HIT_RADIUS + item.getHitRadius());
    }

    @Override
    public String getPhysicalRepresentation() {
        return "{\"shape\" : \"line\", \"startx\": \"" + getStartPoint().getX() + "\", \"starty\" :\"" + getStartPoint().getY()
                + "\", \"endx\": \"" + getEndPoint().getX() + "\", \"endy\": \"" + getEndPoint().getY() + "\"}";
    }
}
