package game.datatypes.weapons;

import java.util.ArrayList;
import java.util.List;

import game.config.WeaponConfig;
import game.datatypes.bullet.LaserBeam;
import game.interfaces.Bullet;
import game.model.PlayerData;
import game.service.Spawner;

public class LaserCannon extends WeaponParent {

	public List<Bullet> createBullet(PlayerData player){
		ArrayList<Bullet> bulletsToCreate = new ArrayList<>();
		
		bulletsToCreate.add(new LaserBeam(player.getX(), player.getY(), player.getShipAngle(), player.getId(), player.getWeapon().getDamage()));
		return bulletsToCreate;
	}
	
	public LaserCannon() {
		Spawner.spawn(this);
		super.setDamage(WeaponConfig.LASER_CANNON_INIT_DAMAGE);
		super.setAmmo(WeaponConfig.LASER_CANNON_INIT_AMMO);
		super.setName("Laser cannon");
		super.setRateOfFire(WeaponConfig.LASER_CANNON_INIT_RATE_OF_FIRE);
	}
}
