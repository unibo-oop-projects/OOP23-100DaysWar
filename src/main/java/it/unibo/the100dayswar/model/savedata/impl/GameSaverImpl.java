package it.unibo.the100dayswar.model.savedata.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.model.savedata.api.GameSaver;

/**
 * Class that implents a game saver that saves all the data
 * storing them in a custom path or in a default one.
 */
public class GameSaverImpl implements GameSaver {
    private static final Logger LOGGER = Logger.getLogger(GameSaverImpl.class.getName());
    private static final String PATH = System.getProperty("user.home") + "/saved_game.ser";
    private final String customPath;
    private final Optional<String> optionalCustomPath;
    private final GameDataImpl currentGameData;

    /**
     * Constructor that initialize the class with the given params.
     * 
     * @param gameDataImpl data of the current game
     * @param customPath custom path of the saving file
     */
    public GameSaverImpl(final GameDataImpl gameDataImpl, final String customPath) {
        this.currentGameData = gameDataImpl;
        this.customPath = customPath;
        this.optionalCustomPath = Optional.ofNullable(this.customPath);
    }
    
    /**
    * Constructor that initializes the class without a custom path.
    * The location of the saving file will be set to the default PATH.
    *
    * @param gameDataImpl Data of the current game.
    */
    public GameSaverImpl(final GameDataImpl gameDataImpl) {
        this.currentGameData = gameDataImpl;
        this.customPath = null;
        this.optionalCustomPath = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveGame() {
        if (optionalCustomPath.isPresent()){
            saveGameAtCustomPath();
        } else {
            saveGameAtDefaultPath();
        }
    }

    /**
     * Saves the saving file in the custom path.
     */
    private void saveGameAtCustomPath() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(customPath))) {
            out.writeObject(currentGameData);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during game serialization and saving.", e);
        }
    }

    /**
     * Saves the saving file in the default PATH.
     */
    private void saveGameAtDefaultPath() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PATH))) {
            out.writeObject(currentGameData);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during game serialization and saving.", e);
        }
    }
}
