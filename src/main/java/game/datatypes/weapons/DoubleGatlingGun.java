package game.datatypes.weapons;

import java.util.ArrayList;
import java.util.List;

import game.config.WeaponConfig;
import game.datatypes.bullet.BulletData;
import game.interfaces.Bullet;
import game.model.PlayerData;
import game.service.Spawner;

public class DoubleGatlingGun extends WeaponParent{

    public List<Bullet> createBullet(PlayerData player){
        ArrayList<Bullet> bulletsToCreate = new ArrayList<>();
        
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
