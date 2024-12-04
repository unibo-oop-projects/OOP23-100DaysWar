package it.unibo.the100dayswar.model.savedata;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import it.unibo.the100dayswar.model.savedata.impl.GameSaverImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.savedata.impl.GameDataImpl;

/**
 * Test suite for GameSaverImpl.
 */
class GameSaverTest {
    private static final String TEST_CUSTOM_PATH = "test_saved_game.ser";
    private static final String DEFAULT_PATH = System.getProperty("user.home") + "/saved_game.ser";

    private final Player mockPlayer1 = new PlayerImpl("MockPlayer1", new CellImpl(new PositionImpl(2, 2), true, true));
    private final Player mockPlayer2 = new PlayerImpl("MockPlayer2", new CellImpl(new PositionImpl(8, 8), true, true));
    private final GameTurnManager mockGameTurnManager = new GameTurnManagerImpl(List.of(mockPlayer1, mockPlayer2));

    private final GameDataImpl mockGameData = new GameDataImpl(mockPlayer2, mockPlayer1, null, mockGameTurnManager);

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
     * @throws IOException
     */
    @Test
    void testSaveGameWithCustomPath() throws IOException {
        final GameSaverImpl saver = new GameSaverImpl(mockGameData, TEST_CUSTOM_PATH);
        saver.saveGame();

        // Verify file creation
        assertTrue(Files.exists(Paths.get(TEST_CUSTOM_PATH)), "File should exist at the custom path.");
    }

    /**
     * Test saving to the default path.
     * @throws IOException
     */
    @Test
    void testSaveGameWithDefaultPath() throws IOException {
        final GameSaverImpl saver = new GameSaverImpl(mockGameData);
        saver.saveGame();

        // Verify file creation
        assertTrue(Files.exists(Paths.get(DEFAULT_PATH)), "File should exist at the default path.");
    }

    /**
     * Test that an IOException is logged when saving fails.
     */
    @Test
    void testSaveGameThrowsIOException() {
        final String invalidPath = "/invalid_path/saved_game.ser";
        final GameSaverImpl saver = new GameSaverImpl(mockGameData, invalidPath);

        final Exception exception = assertThrows(IOException.class, saver::saveGame);

        assertTrue(exception.getMessage().contains("Error during game serialization and saving at " + invalidPath));
    }

    /**
     * Test saving null game data throws an exception.
     */
    @Test
    void testConstructorWithNullGameDataThrowsException() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new GameSaverImpl(null, TEST_CUSTOM_PATH)
        );

        assertTrue(exception.getMessage().contains("Game data must be non-null"));
    }

    /**
     * Test that saving to a custom path overwrites an existing file.
     */
    @Test
    void testSaveGameOverwritesExistingFile() throws IOException {
        Files.createFile(Paths.get(TEST_CUSTOM_PATH));

        final GameSaverImpl saver = new GameSaverImpl(mockGameData, TEST_CUSTOM_PATH);
        saver.saveGame();

        // Verify file is still present
        assertTrue(Files.exists(Paths.get(TEST_CUSTOM_PATH)), "File should be overwritten at the custom path.");
    }
}
