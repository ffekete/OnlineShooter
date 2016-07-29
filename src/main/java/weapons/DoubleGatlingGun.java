package weapons;

import java.util.ArrayList;
import java.util.List;

import config.WeaponConfig;
import model.BulletData;
import model.PlayerData;
import service.Spawner;

public class DoubleGatlingGun extends WeaponParent{

	public List<BulletData> createBullet(PlayerData player){
		ArrayList<BulletData> bulletsToCreate = new ArrayList<>();
		
		bulletsToCreate.add(new BulletData(player.getX(), player.getY(), player.getShipAngle()+0.5d, player.getId(), player.getWeapon().getDamage()));
		bulletsToCreate.add(new BulletData(player.getX(), player.getY(), player.getShipAngle()-0.50d, player.getId(), player.getWeapon().getDamage()));
		
		return bulletsToCreate;
	}
	
	public DoubleGatlingGun(){
		Spawner.spawn(this);
		super.setDamage(WeaponConfig.GATLING_GUN_INIT_DAMAGE);
		super.setAmmo(WeaponConfig.GATLING_GUN_INIT_AMMO);
		super.setName("Double Gatling gun");
		super.setRateOfFire(WeaponConfig.GATLING_GUN_INIT_RATE_OF_FIRE);
	}
} 
