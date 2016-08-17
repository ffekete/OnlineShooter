package game.interfaces;

public interface BulletPoolList<T> extends PoolList<T> {
    public T getNthBullet(int index);

    public void addBullet(Long playerId);
}
