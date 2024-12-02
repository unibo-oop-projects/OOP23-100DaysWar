package it.unibo.the100dayswar.model.savedata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.Player;
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

    private final Player mockPlayer1 = new PlayerImpl("MockPlayer1", new CellImpl(new PositionImpl(0, 0), true, true));
    private final Player mockPlayer2 = new PlayerImpl("MockPlayer2", new CellImpl(new PositionImpl(9, 9), true, true));
    private final MapManager mockGameMapManager = new MapManagerImpl(new GameMapBuilderImpl(MAP_DIMENSION, MAP_DIMENSION));
    private final GameTurnManager mockGameTurnManager = new GameTurnManagerImpl(List.of(mockPlayer1, mockPlayer2));

    private final GameDataImpl mockGameData = new GameDataImpl(mockPlayer1, mockPlayer2, mockGameMapManager, mockGameTurnManager);

    /**
     * Test to ensure the constructor throws an exception when
     * the two players are the same.
     */
    @Test
    void testConstructorWithSamePlayersThrowsException() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new GameDataImpl(mockPlayer1, mockPlayer1, mockGameMapManager, mockGameTurnManager)
        );

        assertTrue(exception.getMessage().contains("playerData1 and playerData2 must be different"));
    }

    /**
     * Test to verify the constructor initializes the fields correctly.
     */
    @Test
    void testConstructorInitializesFields() {
        assertNotNull(mockGameData.getPlayerData1());
        assertNotNull(mockGameData.getPlayerData2());
        assertNotNull(mockGameData.getMapManager());
        assertNotNull(mockGameData.getGameTurnManager());
    }

    /**
     * Test to verify getPlayerData1 returns a deep copy.
     */
    @Test
    void testGetPlayerData1ReturnsDeepCopy() {
        final Player copy = mockGameData.getPlayerData1();

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

        assertEquals(mockGameMapManager.getMapAsAStream().collect(Collectors.toList()), copy.getMapAsAStream().collect(Collectors.toList()));
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
