package it.unibo.the100dayswar.model.loaddata;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.loaddata.impl.GameLoaderImpl;
import it.unibo.the100dayswar.model.savedata.api.GameData;
import it.unibo.the100dayswar.model.savedata.api.GameSaver;
import it.unibo.the100dayswar.model.savedata.impl.GameDataImpl;
import it.unibo.the100dayswar.model.savedata.impl.GameSaverImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.impl.GameMapImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;

/**
 * Test suite for GameLoaderImpl class.
 */
class GameLoaderImplTest {
    private static final String TEST_CUSTOM_PATH = "test_saved_game.ser";
    private static final String DEFAULT_PATH = System.getProperty("user.home") + "/saved_game.ser";
    private static final int MAP_DIMENSION = 10;

    private final Player mockPlayer1 = new PlayerImpl("MockPlayer1", new CellImpl(new PositionImpl(0, 0), true, true));
    private final Player mockPlayer2 = new PlayerImpl("MockPlayer2", new CellImpl(new PositionImpl(9, 9), true, true));
    private final GameMap mockGameMap = new GameMapImpl(MAP_DIMENSION, MAP_DIMENSION, new CellImpl[MAP_DIMENSION][MAP_DIMENSION]);
    private final GameTurnManager mockGameTurnManager = new GameTurnManagerImpl(List.of(mockPlayer1, mockPlayer2));

    private final GameData mockGameData = new GameDataImpl(mockPlayer2, mockPlayer1, mockGameMap, mockGameTurnManager);

    /**
     * Cleanup files after each test.
     *
     * @throws IOException if file deletion fails
     */
    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_CUSTOM_PATH));
        Files.deleteIfExists(Paths.get(DEFAULT_PATH));
    }

    /**
     * Test loading game data from a custom path.
     */
    @Test
    void testLoadGameWithCustomPath() throws IOException, ClassNotFoundException {
        final GameSaver saver = new GameSaverImpl(mockGameData, TEST_CUSTOM_PATH);
        final GameLoaderImpl loader = new GameLoaderImpl(TEST_CUSTOM_PATH);
        final Optional<GameData> loadedData = loader.loadGame();

        saver.saveGame();

        assertTrue(loadedData.isPresent(), "Game data should be loaded successfully.");
        assertEquals(mockGameData, loadedData.get(), "Loaded data should match the saved data.");
    }

    /**
     * Test loading game data from the default path.
     */
    @Test
    void testLoadGameWithDefaultPath() throws IOException, ClassNotFoundException {
        final GameSaver saver = new GameSaverImpl(mockGameData, null);
        final GameLoaderImpl loader = new GameLoaderImpl();
        final Optional<GameData> loadedData = loader.loadGame();

        saver.saveGame();

        assertTrue(loadedData.isPresent(), "Game data should be loaded successfully.");
        assertEquals(mockGameData, loadedData.get(), "Loaded data should match the saved data.");
    }

    /**
     * Test loading game data when the file does not exist.
     */
    @Test
    void testLoadGameFileDoesNotExist() {
        final GameLoaderImpl loader = new GameLoaderImpl(TEST_CUSTOM_PATH);
        final Optional<GameData> loadedData = loader.loadGame();

        assertTrue(!loadedData.isPresent(), "Game data should not be loaded from a non-existent file.");
    }

    /**
     * Test loading game data from a corrupted file.
     */
    @Test
    void testLoadGameFromCorruptedFile() throws IOException {
        try (
            BufferedWriter corruptedFile = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(TEST_CUSTOM_PATH), StandardCharsets.UTF_8
                )
            )
        ) {
            corruptedFile.write("Corrupted_File.");
        }

        final GameLoaderImpl loader = new GameLoaderImpl(TEST_CUSTOM_PATH);
        final Optional<GameData> loadedData = loader.loadGame();

        assertTrue(!loadedData.isPresent(), "Game data should not be loaded from a corrupted file.");
    }
}
