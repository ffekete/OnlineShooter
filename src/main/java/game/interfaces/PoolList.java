package game.interfaces;

import java.util.Iterator;
import java.util.List;

public interface PoolList<T> {
    public boolean add(T t);

    public boolean remove(T t);

    public void clear();

    public int poolSize();

    public Iterator<T> getIterator();

    public List<T> getAllOnScreen(Long playerId);
}