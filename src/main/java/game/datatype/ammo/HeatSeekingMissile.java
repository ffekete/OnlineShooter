package game.datatype.ammo;

import game.config.constant.AmmoType;

public class HeatSeekingMissile extends Missile {

    public HeatSeekingMissile() {
        this.setType(AmmoType.HEAT_SEEKING_MISSILE);
        this.setSpeed(AmmoType.HEAT_SEEKING_MISSILE.getInitSpeed());
    }

    @Override
    public void updateSpeed() {
        this.setSpeed(this.getSpeed() + AmmoType.HEAT_SEEKING_MISSILE.getAcceleration());
        if (this.getSpeed() > AmmoType.HEAT_SEEKING_MISSILE.getMaxSpeed()) {
            this.setSpeed(AmmoType.HEAT_SEEKING_MISSILE.getMaxSpeed());
        }
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