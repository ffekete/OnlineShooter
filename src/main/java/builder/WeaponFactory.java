package builder;

import config.WeaponId;
import interfaces.Weapon;
import weapons.GatlingGun;
import weapons.Machinegun;

public class WeaponFactory {
	
	public Weapon createWeapon(WeaponId weaponId){
		Weapon weapon = null;
		switch(weaponId){
		case MACHINEGUN:
			weapon = new Machinegun();
			break;
			
		case GATLING_GUN:
			weapon = new GatlingGun();
			break;
			
		default:
			break;
		}
		
		return weapon;
	}
}
