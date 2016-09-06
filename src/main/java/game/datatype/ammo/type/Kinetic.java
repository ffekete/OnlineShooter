package game.datatype.ammo.type;

import game.datatype.ammo.AmmoParent;

public abstract class Kinetic extends AmmoParent {

    @Override
    public abstract boolean isAgeCounterExpired();
}
