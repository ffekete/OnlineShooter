package game.interfaces;

import game.datatype.PlayerData;

public interface PlayerDataProcessorInterface {

    /**
     * Main loop function
     * 
     * @throws InterruptedException
     */
    void updatePlayerData() throws InterruptedException;

    void checkIfPlayerGetsAnItem(PlayerData player);

}