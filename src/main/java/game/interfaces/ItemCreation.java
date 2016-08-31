package game.interfaces;

import game.config.constant.ItemType;

public interface ItemCreation {
    public SpawnableItem createItem(ItemType id);
}