package game.interfaces;

import game.datatypes.PlayerData;

public interface PlayerDataProcessorInterface {

    /**
     * Main loop function
     * 
     * @throws InterruptedException
     */
    void updatePlayerData() throws InterruptedException;

    void checkIfPlayerGetsAnItem(PlayerData player);

}