package it.unibo.the100dayswar.model.savedata.api;

import java.io.Serializable;

/**
 * Interface that model a class to save the game data.
 */
public interface GameSaver extends Serializable {
    /**
     * Saves the game data.
     */
    void saveGame();
}
