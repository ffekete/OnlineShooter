package game.interfaces;

import java.util.List;

import game.datatypes.PlayerData;

public interface ItemPoolList<S> extends PoolList<S> {
    public List<S> getAllItemsOnScreen(PlayerData playerData);
}