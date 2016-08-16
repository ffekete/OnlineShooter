package game.datahandler;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.datatypes.PlayerData;
import game.interfaces.Bullet;

@Component
public class BulletPool {

    @Autowired
    PlayerPool playerPool;

    private List<Bullet> bulletPool;

    public void clearPool(){
        bulletPool.clear();
    }
    
    public Iterator<Bullet> getIteratorForBullets(){
        return bulletPool.iterator();
    }
    
    public boolean removeBullet(Bullet bullet){
        boolean status = bulletPool.remove(bullet);
        return status;
    }
    
    public BulletPool() {
        bulletPool = new CopyOnWriteArrayList<Bullet>();
    }

    public List<Bullet> getAllBulletsOnScreen(Long playerId) {
        CopyOnWriteArrayList<Bullet> allBulletsOnScreen = new CopyOnWriteArrayList<Bullet>();

        if (playerId != null) {
            PlayerData player = playerPool.getPlayerById(playerId);
            
            if (player != null) {
                Iterator<Bullet> bit = bulletPool.iterator();

                while (bit.hasNext()) {
                    Bullet actualBullet = bit.next();
                    if ((Math.abs(actualBullet.getX() - player.getX()) <= player.getScreenHalfWidth())
                            && (Math.abs(actualBullet.getY() - player.getY()) <= player.getScreenHalfHeight())) {
                        allBulletsOnScreen.add(actualBullet);
                    }
                }
            }
        }

        return allBulletsOnScreen;
    }

    public Bullet getNthBullet(int index){
        if(index > bulletPool.size()){
            throw new IllegalArgumentException("(E): Index cannot exceed bullet pool size!");
        }
        return bulletPool.get(index);
    }
    
    public int getNuberOfExistingBullets(){
        return bulletPool.size();
    }
    
    public void addBullet(Long playerId) {
        if (playerId != null) {
            PlayerData player = playerPool.getPlayerById(playerId);
            if (player != null && player.canShootWeapon()) {
                List<Bullet> bulletsToCreate = player.createBulletWithPlayerWeapon();

                Iterator<Bullet> it = bulletsToCreate.iterator();
                while (it.hasNext()) {
                    bulletPool.add(it.next());
                }
                player.startShootingRateCooldownEffect();
                player.decreasAmmoForPlayerWeapon(1L);
                if (player.getActualWeaponAmmo() == 0L)
                    player.initWeapon();
            }
        }
    }

}
