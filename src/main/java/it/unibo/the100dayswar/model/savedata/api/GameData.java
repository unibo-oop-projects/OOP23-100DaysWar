package it.unibo.the100dayswar.model.savedata.api;

import java.io.Serializable;

import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;

/**
 * Interface that model a class to save all the data
 * to load the game.
 */
public interface GameData extends Serializable {
    /**
     * Getter for playerData1.
     * 
     * @return the playerData1
     */
    PlayerData getPlayerData1();

     /**
     * Getter for playerData2.
     * 
     * @return the playerData2
     */
    PlayerData getPlayerData2();

     /**
     * Getter for gameMap.
     * 
     * @return the gameMap
     */
    GameMap getGameMap();

    /**
     * Getter for gameTurnManager.
     * 
     * @return the gameTurnManager
     */
    GameTurnManager getGameTurnManager();
}
