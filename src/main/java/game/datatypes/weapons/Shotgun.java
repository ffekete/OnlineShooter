package game.datatypes.weapons;

import java.util.ArrayList;
import java.util.List;

import game.config.WeaponConfig;
import game.datatypes.bullet.BulletData;
import game.interfaces.Bullet;
import game.model.PlayerData;
import game.service.Spawner;

public class Shotgun extends WeaponParent{

	public List<Bullet> createBullet(PlayerData player){
		ArrayList<Bullet> bulletsToCreate = new ArrayList<>();
		
		bulletsToCreate.add(new BulletData(player.getX(), player.getY(), player.getShipAngle()+15.0d, player.getId(), player.getWeapon().getDamage()));
		bulletsToCreate.add(new BulletData(player.getX(), player.getY(), player.getShipAngle()+5.0d, player.getId(), player.getWeapon().getDamage()));
		bulletsToCreate.add(new BulletData(player.getX(), player.getY(), player.getShipAngle(), player.getId(), player.getWeapon().getDamage()));
		bulletsToCreate.add(new BulletData(player.getX(), player.getY(), player.getShipAngle()-5.0d, player.getId(), player.getWeapon().getDamage()));
		bulletsToCreate.add(new BulletData(player.getX(), player.getY(), player.getShipAngle()-15.0d, player.getId(), player.getWeapon().getDamage()));	
		return bulletsToCreate;
	}
	
	public Shotgun(){
		Spawner.spawn(this);
		super.setDamage(WeaponConfig.SHOTGUN_INIT_DAMAGE);
		super.setAmmo(WeaponConfig.SHOTGUN_INIT_AMMO);
		super.setName("Shotgun");
		super.setRateOfFire(WeaponConfig.SHOTGUN_INIT_RATE_OF_FIRE);
	}
} 
