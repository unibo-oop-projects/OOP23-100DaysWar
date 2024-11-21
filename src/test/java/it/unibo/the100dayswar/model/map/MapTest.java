package it.unibo.the100dayswar.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.BuildableCellImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.GameMapImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.cell.impl.BonusCellDecorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

class MapTest {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int BONUS_CELLS_COUNT = 3;
    private static final int OBSTACLE_COUNT = 5;
    private GameMapImpl gameMap;
    private MapManagerImpl mapManager;

    @BeforeEach
    void setUp() {
        final GameMapBuilderImpl mapBuilder;
        mapBuilder = new GameMapBuilderImpl(WIDTH, HEIGHT);
        mapBuilder.initializeBuildableCells()
                  .addSpawnCells()
                  .addBonusCell(BONUS_CELLS_COUNT)
                  .addObstacles(OBSTACLE_COUNT);
        gameMap = (GameMapImpl) mapBuilder.build();
        mapManager = new MapManagerImpl(mapBuilder);
    }

    @Test
    void testGameMapInitialization() {
        assertNotNull(gameMap, "The game map should be initialized.");
        assertEquals(WIDTH, gameMap.getSize().getWidth(), "The map width should match.");
        assertEquals(HEIGHT, gameMap.getSize().getHeight(), "The map height should match.");

        final boolean hasSpawn = gameMap.getAllCells()
                .anyMatch(cell -> cell instanceof BuildableCellImpl && ((BuildableCellImpl) cell).isSpawn());
        assertTrue(hasSpawn, "The map should contain at least one spawn cell.");
    }

    @Test
    void testMapManagerInitialization() {
        assertNotNull(mapManager, "The map manager should be initialized.");
        assertNotNull(mapManager.getPlayersCells(), "The map manager should manage players' cells.");
        assertTrue(mapManager.getPlayersCells().isEmpty(), "No players should own cells initially.");
    }
}
