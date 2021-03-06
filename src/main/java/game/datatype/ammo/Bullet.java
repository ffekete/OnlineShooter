package game.datatype.ammo;

import game.config.constant.AmmoType;
import game.datatype.ammo.type.Kinetic;

public class Bullet extends Kinetic {

    public Bullet() {
        this.setType(AmmoType.BULLET);
        this.setSpeed(AmmoType.BULLET.getInitSpeed());
    }

    @Override
    public boolean isAgeCounterExpired() {
        if (this.getAge() >= AmmoType.BULLET.getInitAge()) {
            this.setAge(0);
            ;
            return true;
        }
        return false;
    }

    @Override
    public void updateSpeed() {
        this.setSpeed(this.getSpeed() + AmmoType.BULLET.getAcceleration());
        if (this.getSpeed() > AmmoType.BULLET.getMaxSpeed()) {
            this.setSpeed(AmmoType.BULLET.getMaxSpeed());
        }
    }

    @Override
    public String getPhysicalRepresentation() {
        return new String("{\"shape\": \"circle\", \"startx\": \"" + this.getCoordinate().getX() + "\", \"starty\": \""
                + this.getCoordinate().getY() + "\", \"radius\" : \"2\"}");
    }
}
