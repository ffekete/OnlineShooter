package game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.datahandler.ItemPool;
import game.interfaces.ItemProcessorInterface;

@Component
public class ItemProcessor implements ItemProcessorInterface {
    
    @Autowired
    ItemPool itemPool;
    
    /* (non-Javadoc)
     * @see game.service.ItemProcessorInterface#updateItemData()
     */
    @Override
    public void updateItemData(){
        itemPool.createNewRandomItem();
    }
    
}
