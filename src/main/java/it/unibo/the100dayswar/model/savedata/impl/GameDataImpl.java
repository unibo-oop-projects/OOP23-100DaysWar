package it.unibo.the100dayswar.model.savedata.impl;

import it.unibo.the100dayswar.model.savedata.api.GameData;
import it.unibo.the100dayswar.model.savedata.api.PlayerData;

/**
 * Class that saves all the data that need to be serialized.
 */
public class GameDataImpl implements GameData {
    private final PlayerData playerData1;
    private final PlayerData playerData2;

    /**
     * Constructor of GameDataImpl, initializes the object
     * with the given params.
     * 
     * @param playerData1 the player to save
     * @param playerData2 the towers of the player to save
     */
    public GameDataImpl(final PlayerData playerData1, final PlayerData playerData2) {
        if (playerData1.equals(playerData2)) {
            throw new IllegalArgumentException("player1 and player2 must be different");
        }

        this.playerData1 = playerData1;
        this.playerData2 = playerData2;
    }

    /**
     * Getter for playerData1.
     * 
     * @return the playerData1
     */
    public PlayerData getPlayerData1() {
        return playerData1;
    }

     /**
     * Getter for playerData2.
     * 
     * @return the playerData2
     */
    public PlayerData getPlayerData2() {
        return playerData2;
    }
}
