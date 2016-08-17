package game.interfaces;

import java.util.List;
import java.util.Set;

public interface PoolMap<T1, T2> {
    public Set<T1> getAll();

    public void put(T1 t1, T2 t2);

    public void remove(T1 t1);

    public T2 get(T1 t1);

    public List<T2> getAllOnScreen(T1 t1);
}