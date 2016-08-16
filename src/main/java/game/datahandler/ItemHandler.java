package game.datahandler;

import org.springframework.stereotype.Component;

import game.datatypes.PlayerData;
import game.interfaces.Spawnable;

@Component
public class ItemHandler {

    public boolean isItOnScreen(PlayerData player, Spawnable item){
        boolean result = false;
        
        if ((Math.abs(item.getX() - player.getX()) <= player.getScreenHalfWidth())
                && (Math.abs(item.getY() - player.getY()) <= player.getScreenHalfHeight())) {
            result = true;
        }
        
        return result;
    }
}
