package game.datatype.ammo;

import game.config.constant.AmmoConfig;
import game.config.constant.AmmoType;

public class Bullet extends Kinetic {
	
	public Bullet() {
		this.setType(AmmoType.BULLET);
		this.setSpeed(AmmoConfig.BULLET_INIT_SPEED);
	}

    @Override
    public boolean isAgeCounterExpired() {
        if (this.getAge() >= AmmoConfig.BULLET_INIT_AGE) {
            this.setAge(0);;
            return true;
        }
        return false;
    }
    
    @Override
    public String getPhysicalRepresentation() {
        return new String("{\"shape\": \"circle\", \"startx\": \"" + this.getCoordinate().getX() + "\", \"starty\": \""
                + this.getCoordinate().getY() + "\", \"radius\" : \"2\"}");
    }
}
