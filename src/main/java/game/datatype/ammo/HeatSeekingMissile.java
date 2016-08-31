package game.datatype.ammo;

import game.config.constant.AmmoConfig;
import game.config.constant.AmmoType;

public class HeatSeekingMissile extends Explosive {

	public HeatSeekingMissile() {
		this.setType(AmmoType.HEAT_SEEKING_MISSILE);
		this.setSpeed(AmmoConfig.HEAT_SEEKING_MISSILE_INIT_SPEED);
	}

    @Override
    public boolean isAgeCounterExpired() {
        if (this.getAge() >= AmmoConfig.HEAT_SEEKING_MISSILE_INIT_AGE) {
            this.setAge(0);;
            return true;
        }
        return false;
    }
    
    @Override
    public String getPhysicalRepresentation() {
        return new String("{\"shape\": \"circle\", \"startx\": \"" + this.getCoordinate().getX() + "\", \"starty\": \""
                + this.getCoordinate().getY() + "\", \"radius\" : \"5\"}");
    }
}