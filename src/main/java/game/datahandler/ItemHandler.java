package game.datahandler;

import game.datatype.PlayerData;
import game.interfaces.Spawnable;

public class ItemHandler {

    public boolean isItOnScreen(PlayerData player, Spawnable item) {
        boolean result = false;

        if ((Math.abs(item.getX() - player.getX()) <= player.getScreenHalfWidth())
                && (Math.abs(item.getY() - player.getY()) <= player.getScreenHalfHeight())) {
            result = true;
        }

        return result;
    }
}