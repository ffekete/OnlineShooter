package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import datahandler.ItemPool;

@Component
public class ItemProcessor {
	
	@Autowired
	ItemPool itemPool;
	
	public void updateItemData(){
		itemPool.createNewRandomItem();
	}
}
