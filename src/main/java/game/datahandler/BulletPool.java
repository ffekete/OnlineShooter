package game.datahandler;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.datatype.PlayerData;
import game.interfaces.Bullet;
import game.interfaces.BulletPoolList;
import game.interfaces.PlayerPoolMap;

@Component
public class BulletPool implements BulletPoolList<Bullet> {

    @Autowired
    PlayerPoolMap<Long, PlayerData> playerPool;

    private List<Bullet> bulletPool;

    public BulletPool() {
        bulletPool = new CopyOnWriteArrayList<Bullet>();
    }
    
    @Override
    public Iterator<Bullet> getIterator() {
        return bulletPool.iterator();
    }
    
    @Override
    public List<Bullet> getAllOnScreen(Long playerId) {
        CopyOnWriteArrayList<Bullet> allBulletsOnScreen = new CopyOnWriteArrayList<Bullet>();

        PlayerData player = playerPool.get(playerId);

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

        return allBulletsOnScreen;
    }

    @Override
    public Bullet getNthBullet(int index) {
        if (index > bulletPool.size()) {
            throw new IllegalArgumentException("(E): Index cannot exceed bullet pool size!");
        }
        return bulletPool.get(index);
    }
    @Override
    public int poolSize() {
        return bulletPool.size();
    }
    
    @Override
    public void addBullet(Long playerId) {
        PlayerData player = playerPool.get(playerId);
        if (player != null && player.canShootWeapon()) {
            this.createBulletsForPlayer(player);
        }
    }

    private void createBulletsForPlayer(PlayerData player) {
        List<Bullet> bulletsToCreate = player.createBulletWithPlayerWeapon();

        Iterator<Bullet> it = bulletsToCreate.iterator();
        while (it.hasNext()) {
            this.add(it.next());
        }
        player.startShootingRateCooldownEffect();
        player.decreasAmmoForPlayerWeapon(1L);
        if (player.getActualWeaponAmmo() == 0L) {
            player.initWeapons();
        }
    }

    @Override
    public boolean add(Bullet bullet) {
        return this.bulletPool.add(bullet);
    }

    @Override
    public boolean remove(Bullet bullet) {
        return this.bulletPool.remove(bullet);
    }

    @Override
    public void clear() {
        this.bulletPool.clear();
    }
}
