package it.unibo.the100dayswar.model.savedata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
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
     * Test to verify getPlayerData1 returns a deep copy.
     */
    @Test
    void testGetPlayerData1ReturnsDeepCopy() {
        final Player copy = mockGameData.getHumanData();

        assertNotSame(mockPlayer1, copy);

        assertEquals(mockPlayer1.getBankAccount(), copy.getBankAccount());
        assertEquals(mockPlayer1.getSoldiers(), copy.getSoldiers());
        assertEquals(mockPlayer1.getSpawnPoint(), copy.getSpawnPoint());
        assertEquals(mockPlayer1.getTowers(), copy.getTowers());
        assertEquals(mockPlayer1.getUnits(), copy.getUnits());
        assertEquals(mockPlayer1.getUsername(), copy.getUsername());
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
