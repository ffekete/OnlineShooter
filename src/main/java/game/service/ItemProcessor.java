package game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.interfaces.ItemPoolList;
import game.interfaces.ItemProcessorInterface;
import game.interfaces.SpawnableItem;

@Component
public class ItemProcessor implements ItemProcessorInterface {

    @Autowired
    ItemPoolList<SpawnableItem> itemPool;

    /*
     * (non-Javadoc)
     * 
     * @see game.service.ItemProcessorInterface#updateItemData()
     */
    @Override
    public void updateItemData() {
        itemPool.createNewRandomItem();
    }
}