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
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.impl.GameMapImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.savedata.impl.GameDataImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;

import java.awt.Dimension;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test suite for GameDataImpl class.
 */
class GameDataTest {
    private static final int MAP_DIMENSION = 10;

    private final Player mockPlayer1 = new PlayerImpl("MockPlayer1", new CellImpl(new PositionImpl(0, 0), true, true));
    private final Player mockPlayer2 = new PlayerImpl("MockPlayer2", new CellImpl(new PositionImpl(9, 9), true, true));
    private final GameMap mockGameMap = new GameMapImpl(MAP_DIMENSION, MAP_DIMENSION, new CellImpl[MAP_DIMENSION][MAP_DIMENSION]);
    private final GameTurnManager mockGameTurnManager = new GameTurnManagerImpl(List.of(mockPlayer1, mockPlayer2));

    private final GameDataImpl mockGameData = new GameDataImpl(mockPlayer1, mockPlayer2, mockGameMap, mockGameTurnManager);

    /**
     * Test to ensure the constructor throws an exception when
     * the two players are the same.
     */
    @Test
    void testConstructorWithSamePlayersThrowsException() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new GameDataImpl(mockPlayer1, mockPlayer1, mockGameMap, mockGameTurnManager)
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
        assertNotNull(mockGameData.getGameMap());
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
     * Test to verify getGameMap returns a deep copy.
     */
    @Test
    void testGetGameMapReturnsDeepCopy() {
        final GameMap copy = mockGameData.getGameMap();

        assertNotSame(mockGameMap, copy);

        assertEquals(new Dimension(MAP_DIMENSION, MAP_DIMENSION), copy.getSize());
        assertEquals(mockGameMap.getAllCells().collect(Collectors.toList()), copy.getAllCells().collect(Collectors.toList()));
    }

    /**
     * Test to verify getGameTurnManager returns the same instance.
     */
    @Test
    void testGetGameTurnManagerReturnsSameInstance() {
        assertSame(mockGameTurnManager, mockGameData.getGameTurnManager());
    }
}

