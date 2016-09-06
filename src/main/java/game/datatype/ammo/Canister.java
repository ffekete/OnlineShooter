package game.datatype.ammo;

import game.config.constant.AmmoType;
import game.datatype.ammo.type.Kinetic;

public class Canister extends Kinetic {

    public Canister() {
        this.setType(AmmoType.CANISTER);
        this.setSpeed(AmmoType.CANISTER.getInitSpeed());
    }

    @Override
    public boolean isAgeCounterExpired() {
        if (this.getAge() >= AmmoType.CANISTER.getInitAge()) {
            this.setAge(0);
            return true;
        }
        return false;
    }

    @Override
    public void updateSpeed() {
        this.setSpeed(this.getSpeed() + AmmoType.CANISTER.getAcceleration());
        if (this.getSpeed() > AmmoType.CANISTER.getMaxSpeed()) {
            this.setSpeed(AmmoType.CANISTER.getMaxSpeed());
        }
    }

    @Override
    public String getPhysicalRepresentation() {
        return new String("{\"shape\": \"circle\", \"startx\": \"" + this.getCoordinate().getX() + "\", \"starty\": \""
                + this.getCoordinate().getY() + "\", \"radius\" : \"1\"}");
    }
}
