package game.interfaces;

import java.util.Iterator;

import game.datatypes.RegistrationData;

public interface PlayerPoolMap<T1, T2> extends PoolMap<T1, T2> {
    public void resetInactivityOfPlayer(T1 t1);

    public boolean registerPlayer(T1 t1, RegistrationData data);

    public void updatePlayerPoolData();

    public Iterator<T1> getKeySetIterator();
}
