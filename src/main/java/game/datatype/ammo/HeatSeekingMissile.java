package game.datatype.ammo;

import game.config.constant.AmmoType;
import game.datatype.ammo.type.Explosive;

public class HeatSeekingMissile extends Missile {

    public HeatSeekingMissile() {
        this.setType(AmmoType.HEAT_SEEKING_MISSILE);
        this.setSpeed(AmmoType.HEAT_SEEKING_MISSILE.getInitSpeed());
    }

    @Override
    public boolean isAgeCounterExpired() {
        if (this.getAge() >= AmmoType.HEAT_SEEKING_MISSILE.getInitAge()) {
            this.setAge(0);
            ;
            return true;
        }
        return false;
    }
}