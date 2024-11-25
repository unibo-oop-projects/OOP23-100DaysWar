package it.unibo.the100dayswar.model.savedata;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.*;
import java.util.List;

import org.junit.jupiter.api.*;

import it.unibo.the100dayswar.model.savedata.impl.GameSaverImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.impl.GameMapImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.savedata.impl.GameDataImpl;

/**
 * Tests the class GameSaverImpl.
 */
class GameSaverTest {

    private static final String TEST_CUSTOM_PATH = "test_saved_game.ser";
    private static final String DEFAULT_PATH = System.getProperty("user.home") + "/saved_game.ser";

    final Player mockPlayer1 = new PlayerImpl("MockPlayer1", new CellImpl(new PositionImpl(0, 0), true, true));
    final Player mockPlayer2 = new PlayerImpl("MockPlayer2", new CellImpl(new PositionImpl(9, 9), true, true));
    final GameMap mockGameMap = new GameMapImpl(10, 10, new CellImpl[10][10]);
    final GameTurnManager mockGameTurnManager = new GameTurnManagerImpl(List.of(mockPlayer1, mockPlayer2));

    final GameDataImpl mockGameData = new GameDataImpl(mockPlayer1, mockPlayer2, mockGameMap, mockGameTurnManager);

    /**
     * Cleans up the files.
     * 
     * @throws IOException
     */
    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_CUSTOM_PATH));
        Files.deleteIfExists(Paths.get(DEFAULT_PATH));
    }

    /**
     * Test saving to a custom path.
     */
    @Test
    void testSaveGameWithCustomPath() {
        final GameSaverImpl saver = new GameSaverImpl(mockGameData, TEST_CUSTOM_PATH);
        saver.saveGame();

        // Verify file creation
        assertTrue(Files.exists(Paths.get(TEST_CUSTOM_PATH)), "File should exist at the custom path.");
    }

    /**
     * Test saving to the default path.
     */
    @Test
    void testSaveGameWithDefaultPath() {
        final GameSaverImpl saver = new GameSaverImpl(mockGameData);
        saver.saveGame();

        // Verify file creation
        assertTrue(Files.exists(Paths.get(DEFAULT_PATH)), "File should exist at the default path.");
    }

    /**
     * Test that an IOException is logged when saving fails.
     */
    @Test
    void testSaveGameHandlesIOException() {
        final GameSaverImpl saver = new GameSaverImpl(mockGameData, "/invalid_path/saved_game.ser");

        // Redirect logging to check errors
        ByteArrayOutputStream logStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(logStream));

        saver.saveGame();

        // Check for error log
        final String logOutput = logStream.toString();
        assertTrue(logOutput.contains("Error during game serialization and saving at "), "Error should be logged when saving fails.");
    }

    /**
     * Test saving null game data throws an exception.
     */
    @Test
    void testConstructorWithNullGameDataThrowsException() {
        Exception exception = assertThrows(NullPointerException.class, () -> 
            new GameSaverImpl(null, TEST_CUSTOM_PATH)
        );

        assertTrue(exception.getMessage().contains("Game data must be non-null"));
    }

    /**
     * Test that saving to a custom path overwrites an existing file.
     */
    @Test
    void testSaveGameOverwritesExistingFile() throws IOException {
        // Pre-create the file
        Files.createFile(Paths.get(TEST_CUSTOM_PATH));

        final GameSaverImpl saver = new GameSaverImpl(mockGameData, TEST_CUSTOM_PATH);
        saver.saveGame();

        // Verify file is still present
        assertTrue(Files.exists(Paths.get(TEST_CUSTOM_PATH)), "File should be overwritten at the custom path.");
    }
}
