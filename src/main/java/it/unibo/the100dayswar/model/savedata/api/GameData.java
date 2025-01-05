package it.unibo.the100dayswar.model.savedata.api;

import java.io.Serializable;

import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;

/**
 * Interface that model a class to save all the data
 * to load the game.
 */
public interface GameData extends Serializable {
    /**
     * Getter for playerData.
     * 
     * @return the playerData
     */
    Player getPlayerData();

     /**
     * Getter for botData.
     * 
     * @return the botData
     */
    Player getBotData();

     /**
     * Getter for mapManager.
     * 
     * @return the mapManager
     */
    MapManager getMapManager();

    /**
     * Getter for gameTurnManager.
     * 
     * @return the gameTurnManager
     */
    GameTurnManager getGameTurnManager();
}
