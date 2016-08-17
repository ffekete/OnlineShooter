package game.interfaces;

import java.util.Iterator;

public interface BulletPoolList<S> extends PoolList<S> {
    public S getNthBullet(int index);
    
    public Iterator<S> getIteratorForBullets();
}
