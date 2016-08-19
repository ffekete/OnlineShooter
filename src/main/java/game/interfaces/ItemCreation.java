package game.interfaces;

import game.config.constant.SpawnableItemType;

public interface ItemCreation {
    public SpawnableItem createItem(SpawnableItemType id);
}