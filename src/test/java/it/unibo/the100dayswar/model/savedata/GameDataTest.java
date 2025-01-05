package it.unibo.the100dayswar.model.savedata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.savedata.impl.GameDataImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Test suite for GameDataImpl class.
 */
class GameDataTest {
    private static final int MAP_DIMENSION = 10;

    private final MapManager mockGameMapManager = new MapManagerImpl(new GameMapBuilderImpl(MAP_DIMENSION, MAP_DIMENSION));
    private final BotPlayer mockBotPlayer = new SimpleBot(mockGameMapManager);
    private final HumanPlayer mockHumanPlayer = new HumanPlayerImpl("Mock human player", mockGameMapManager.getPlayerSpawn());
    private final GameTurnManager mockGameTurnManager = new GameTurnManagerImpl(List.of(mockBotPlayer, mockHumanPlayer));
    private final GameDataImpl mockGameData = new GameDataImpl(mockHumanPlayer, mockBotPlayer, mockGameMapManager, mockGameTurnManager);

    /**
     * Test to verify the constructor initializes the fields correctly.
     */
    @Test
    void testConstructorInitializesFields() {
        assertNotNull(mockGameData.getHumanData());
        assertNotNull(mockGameData.getBotData());
        assertNotNull(mockGameData.getMapManager());
        assertNotNull(mockGameData.getGameTurnManager());
    }

    /**
     * Test to verify getHumanData returns a deep copy.
     */
    @Test
    void testGetPlayerDataReturnsDeepCopy() {
        final Player copy = mockGameData.getHumanData();

        assertNotSame(mockHumanPlayer, copy);

        assertEquals(mockHumanPlayer.getBankAccount(), copy.getBankAccount());
        assertEquals(mockHumanPlayer.getSoldiers(), copy.getSoldiers());
        assertEquals(mockHumanPlayer.getSpawnPoint(), copy.getSpawnPoint());
        assertEquals(mockHumanPlayer.getTowers(), copy.getTowers());
        assertEquals(mockHumanPlayer.getUnits(), copy.getUnits());
        assertEquals(mockHumanPlayer.getUsername(), copy.getUsername());
    }

    /**
     * Test to verify getBotData returns a deep copy.
     */
    @Test
    void testGetBotDataReturnsDeepCopy() {
        final Player copy = mockGameData.getBotData();

        assertNotSame(mockBotPlayer, copy);

        assertEquals(mockBotPlayer.getBankAccount(), copy.getBankAccount());
        assertEquals(mockBotPlayer.getSoldiers(), copy.getSoldiers());
        assertEquals(mockBotPlayer.getSpawnPoint(), copy.getSpawnPoint());
        assertEquals(mockBotPlayer.getTowers(), copy.getTowers());
        assertEquals(mockBotPlayer.getUnits(), copy.getUnits());
        assertEquals(mockBotPlayer.getUsername(), copy.getUsername());
    }

    /**
     * Test to verify getMapManager returns a deep copy.
     */
    @Test
    void testGetGameMapReturnsDeepCopy() {
        final MapManager copy = mockGameData.getMapManager();

        assertNotSame(mockGameMapManager, copy);

        assertEquals(
            mockGameMapManager.getMapAsAStream().collect(Collectors.toList()),
            copy.getMapAsAStream().collect(Collectors.toList())
        );

        assertEquals(mockGameMapManager.getPlayersCells(), copy.getPlayersCells());
    }

    /**
     * Test to verify getGameTurnManager returns the same instance.
     */
    @Test
    void testGetGameTurnManagerReturnsSameInstance() {
        assertSame(mockGameTurnManager, mockGameData.getGameTurnManager());
    }
}
