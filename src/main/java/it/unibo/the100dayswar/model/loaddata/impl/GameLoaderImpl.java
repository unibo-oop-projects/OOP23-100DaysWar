package it.unibo.the100dayswar.model.loaddata.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.model.loaddata.api.GameLoader;
import it.unibo.the100dayswar.model.savedata.api.GameData;

/**
 * Class that implements a game loader to load all game data from a saved file.
 */
public class GameLoaderImpl implements GameLoader {
    private static final Logger LOGGER = Logger.getLogger(GameLoaderImpl.class.getName());
    private static final String DEFAULT_PATH = System.getProperty("user.home") + "/saved_game.ser";
    private final Optional<String> customPath;

    /**
     * Constructor that initializes the loader with an optional custom path.
     *
     * @param customPath the custom path of the saved file.
     */
    public GameLoaderImpl(final String customPath) {
        this.customPath = Optional.ofNullable(customPath);
    }

    /**
     * Constructor that initializes the loader with the default path.
     */
    public GameLoaderImpl() {
        this.customPath = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameData> loadGame() {
        if (customPath.isPresent()) {
            return loadGameAtCustomPath(customPath);
        } else {
            return loadGameAtDefaultPath();
        }
    }

    /**
     * Gives a gameData object wrapped in a Optional 
     * that contains all the informations extracted 
     * from the file in which the data are stored.
     * The file is in DEFAULT_PATH.
     * 
     * @return a gameData object wrapped in a Optional
     */
    private Optional<GameData> loadGameAtDefaultPath() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DEFAULT_PATH))) {
            return Optional.of((GameData) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error during game deserialization and loading.", e);
            return Optional.empty();
        }
    }

    /**
     * Gives a gameData object wrapped in a Optional 
     * that contains all the informations extracted 
     * from the file in which the data are stored.
     * The file is in customPath.
     * 
     * @param customPath the path of the file
     * @return a gameData object wrapped in a Optional
     */
    private Optional<GameData> loadGameAtCustomPath(final Optional<String> customPath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(customPath.get()))) {
            return Optional.of((GameData) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error during game deserialization and loading.", e);
            return Optional.empty();
        }
    }
}

