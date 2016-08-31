package game.config.constant;

public enum AmmoType {
	// Kinetic types
	BULLET(AmmoConfig.BULLET_INIT_DAMAGE, AmmoConfig.BULLET_DAMAGE_BONUS),
	CANISTER(AmmoConfig.CANISTER_INIT_DAMAGE, AmmoConfig.CANISTER_DAMAGE_BONUS),
	
	// Energy types
	LASER_BEAM(AmmoConfig.LASER_BEAM_INIT_DAMAGE, AmmoConfig.LASER_BEAM_DAMAGE_BONUS),
	
	// Explosive types
	HEAT_SEEKING_MISSILE(AmmoConfig.HEAT_SEEKING_MISSILE_INIT_DAMAGE, AmmoConfig.HEAT_SEEKING_MISSILE_DAMAGE_BONUS),
	
	NO_AMMO(AmmoConfig.NO_AMMO_INIT_DAMAGE, AmmoConfig.NO_AMMO_DAMAGE_BONUS);
	
	private double initDamage;
	private double damageBonus;
	
	private AmmoType(double initDamage, double damageBonus) {
		this.initDamage = initDamage;
		this.damageBonus = damageBonus;
	}
	
	public double getDamage(long bonus) {
		if (bonus > 0) {
			return this.initDamage + this.damageBonus * bonus;
		} else {
			return this.initDamage;
		}
	}
    
    public static int count() {
        return values().length;
    }

    public static AmmoType get(int index) {
        if (index >= 0 && index < count())
            return values()[index];
        else
            throw new IllegalArgumentException();
    }
}