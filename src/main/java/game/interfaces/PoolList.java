package game.interfaces;

public interface PoolList<T> {
    public boolean add(T t);

    public boolean remove(T t);

    public void clear();
    
    public int poolSize();
}