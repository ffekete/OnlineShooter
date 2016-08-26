package game.datahandler;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.datatype.PlayerData;
import game.interfaces.Ammo;
import game.interfaces.AmmoPoolList;
import game.interfaces.PlayerPoolMap;

@Component
public class AmmoPool implements AmmoPoolList<Ammo> {

    @Autowired
    PlayerPoolMap<Long, PlayerData> playerPool;

    private List<Ammo> ammoPool;

    public AmmoPool() {
        ammoPool = new CopyOnWriteArrayList<Ammo>();
    }
    
    @Override
    public Iterator<Ammo> getIterator() {
        return ammoPool.iterator();
    }
    
    @Override
    public List<Ammo> getAllOnScreen(Long playerId) {
        CopyOnWriteArrayList<Ammo> allAmmoOnScreen = new CopyOnWriteArrayList<Ammo>();

        PlayerData player = playerPool.get(playerId);

        if (player != null) {
            Iterator<Ammo> bit = ammoPool.iterator();

            while (bit.hasNext()) {
                Ammo actualBullet = bit.next();
                if ((Math.abs(actualBullet.getX() - player.getX()) <= player.getScreenHalfWidth())
                        && (Math.abs(actualBullet.getY() - player.getY()) <= player.getScreenHalfHeight())) {
                    allAmmoOnScreen.add(actualBullet);
                }
            }
        }

        return allAmmoOnScreen;
    }

    @Override
    public Ammo getNthAmmo(int index) {
        if (index > ammoPool.size()) {
            throw new IllegalArgumentException("(E): Index cannot exceed ammo pool size!");
        }
        return ammoPool.get(index);
    }
    @Override
    public int poolSize() {
        return ammoPool.size();
    }
    
    @Override
    public void addAmmo(Long playerId) {
        PlayerData player = playerPool.get(playerId);
        if (player != null && player.canShootWeapon()) {
            this.createAmmoForPlayer(player);
        }
    }

    private void createAmmoForPlayer(PlayerData player) {
        List<Ammo> bulletsToCreate = player.createAmmoWithPlayerWeapon();

        Iterator<Ammo> it = bulletsToCreate.iterator();
        while (it.hasNext()) {
            this.add(it.next());
        }
        player.startShootingRateCooldownEffect();
        player.decreasAmmoForPlayerWeapon();
    }

    @Override
    public boolean add(Ammo bullet) {
        return this.ammoPool.add(bullet);
    }

    @Override
    public boolean remove(Ammo bullet) {
        return this.ammoPool.remove(bullet);
    }

    @Override
    public void clear() {
        this.ammoPool.clear();
    }
}
