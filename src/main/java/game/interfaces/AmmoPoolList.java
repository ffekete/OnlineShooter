package game.interfaces;

public interface AmmoPoolList<T> extends PoolList<T> {
    public T getNthAmmo(int index);

    public void addAmmo(Long playerId);
}
