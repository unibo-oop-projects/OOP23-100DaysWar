package it.unibo.the100dayswar.model.savedata.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.model.savedata.api.GameSaver;

/**
 * Class that implements a game saver that saves all the data,
 * storing them in a custom path or in a default one.
 */
public class GameSaverImpl implements GameSaver {
    private static final Logger LOGGER = Logger.getLogger(GameSaverImpl.class.getName());
    private static final String PATH = System.getProperty("user.home") + "/saved_game.ser";
    private final String customPath;

    private final GameDataImpl currentGameData;

    /**
     * Constructor that initializes the class with the given parameters.
     * 
     * @param gameDataImpl data of the current game
     * @param customPath custom path of the saving file
     */
    public GameSaverImpl(final GameDataImpl gameDataImpl, final String customPath) {
        this.currentGameData = gameDataImpl;
        this.customPath = customPath;
    }

    /**
     * Constructor that initializes the class without a custom path.
     * The location of the saving file will be set to the default PATH.
     *
     * @param gameDataImpl Data of the current game.
     */
    public GameSaverImpl(final GameDataImpl gameDataImpl) {
        this(gameDataImpl, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveGame() {
        final String path = (customPath != null) ? customPath : PATH;
        saveGameAtPath(path);
    }

    /**
     * Saves the game data to the specified path.
     * 
     * @param path the path where the game data will be saved
     */
    private void saveGameAtPath(final String path) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(currentGameData);
            LOGGER.log(Level.INFO, "Game saved successfully at " + path);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during game serialization and saving at " + path, e);
        }
    }
}
