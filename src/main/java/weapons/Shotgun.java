package weapons;

import java.util.ArrayList;
import java.util.List;

import bullet.BulletData;
import config.WeaponConfig;
import interfaces.Bullet;
import model.PlayerData;
import service.Spawner;

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
