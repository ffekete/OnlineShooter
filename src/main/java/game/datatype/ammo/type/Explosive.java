package game.datatype.ammo.type;

import game.datatype.ammo.AmmoParent;

public abstract class Explosive extends AmmoParent {
	private double hitRadius;

	@Override
    public abstract boolean isAgeCounterExpired();

	public double getHitRadius() {
		return hitRadius;
	}

	public void setHitRadius(double hitRadius) {
		this.hitRadius = hitRadius;
	}
}
