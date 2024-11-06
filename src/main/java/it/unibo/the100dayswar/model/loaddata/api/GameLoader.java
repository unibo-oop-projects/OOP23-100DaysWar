package it.unibo.the100dayswar.model.loaddata.api;

import java.util.Optional;

import it.unibo.the100dayswar.model.savedata.api.GameData;

/**
 * Interface that model a game loader.
 * The game loadere has the to extract the saved data by 
 * the game saver and load them in the current istance of
 * the program.
 */
public interface GameLoader {
    /**
     * Loads the game data from the saved file.
     *
     * @return the loaded GameDataImpl object wrapped
     * in an Optional, or Optional.epty() if an error occurs.
     */
    Optional<GameData> loadGame();
}
