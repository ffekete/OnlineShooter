package game.datatype.ammo;

import game.config.constant.AmmoType;
import game.datatype.ammo.type.Explosive;

public class Missile extends Explosive {
    public Missile() {
        this.setType(AmmoType.MISSILE);
        this.setSpeed(AmmoType.MISSILE.getInitSpeed());
    }
    
    @Override
    public void updateSpeed() {
        this.setSpeed(this.getSpeed() + AmmoType.MISSILE.getAcceleration());
        if (this.getSpeed() > AmmoType.MISSILE.getMaxSpeed()) {
            this.setSpeed(AmmoType.MISSILE.getMaxSpeed());
        }
    }

    @Override
    public boolean isAgeCounterExpired() {
        if (this.getAge() >= AmmoType.MISSILE.getInitAge()) {
            this.setAge(0);
            ;
            return true;
        }
        return false;
    }

    @Override
    public String getPhysicalRepresentation() {
        return new String("{\"shape\": \"missile\", \"startx\": \"" + this.getCoordinate().getX() + "\", \"starty\": \""
                + this.getCoordinate().getY() + "\", \"radius\" : \"5\"}");
    }
}
