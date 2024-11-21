package it.unibo.the100dayswar.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testPlayerOwnership() {
        final BuildableCellImpl spawnCell = new BuildableCellImpl(new PositionImpl(0, 0), true, true);
        final PlayerImpl player = new PlayerImpl("Player1", spawnCell);
        final SoldierImpl soldier = new SoldierImpl(player);

        mapManager.update(new Pair<>(soldier, spawnCell));

        final Set<Cell> playerCells = mapManager.getPlayersCells().get(player);
        assertNotNull(playerCells, "The player should own some cells.");
        assertTrue(playerCells.contains(spawnCell), "The player should own their spawn cell.");
    }

    @Test
    void testBonusCellActivation() {
        final Cell bonusCell = gameMap.getAllCells()
                .filter(cell -> cell instanceof BonusCellDecorator)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No bonus cells found in the map."));
        final BuildableCellImpl baseCell = (BuildableCellImpl) bonusCell;
        final PlayerImpl player = new PlayerImpl("Player1", baseCell);
        final SoldierImpl soldier = new SoldierImpl(player);

        baseCell.setOccupation(Optional.of(soldier));
        ((BonusCellDecorator) bonusCell).notify(player);

        assertFalse(((BonusCellDecorator) bonusCell).isBonusActive(), "The bonus cell should be inactive after activation.");
    }

    @Test
    void testSoldierMovement() {
        final BuildableCellImpl startCell = new BuildableCellImpl(new PositionImpl(0, 0), true, true);
        final BuildableCellImpl targetCell = new BuildableCellImpl(new PositionImpl(0, 1), true, false);
        final PlayerImpl player = new PlayerImpl("Player1", startCell);
        final SoldierImpl soldier = new SoldierImpl(player);

        mapManager.update(new Pair<>(soldier, startCell));
        mapManager.update(new Pair<>(soldier, targetCell));

        assertTrue(targetCell.getUnit().isPresent(), "The target cell should now contain the soldier.");
        assertFalse(startCell.getUnit().isPresent(), "The start cell should no longer contain the soldier.");
    }

    @Test
    void testSpawnCellOccupiedByNewSoldier() {
    final BuildableCellImpl spawnCell = new BuildableCellImpl(new PositionImpl(0, 0), true, true);
    final PlayerImpl player = new PlayerImpl("FirstPlayer", spawnCell);
    final SoldierImpl firstSoldier = new SoldierImpl(player);

    mapManager.update(new Pair<>(firstSoldier, spawnCell));

    assertTrue(spawnCell.getUnit().isPresent(), "The spawn cell should contain the first soldier.");

    final SoldierImpl secondSoldier = new SoldierImpl(player);
    final Exception exception = assertThrows(IllegalStateException.class, () -> {
        mapManager.update(new Pair<>(secondSoldier, spawnCell));
    });

    assertEquals("Spawn cell is occupied. Move the existing soldier before creating a new one.", exception.getMessage());
    assertTrue(spawnCell.getUnit().isPresent(), "The spawn cell should still contain the first soldier.");
}
    @Test
    void testObstaclePlacement() {
        final long obstacleCount = gameMap.getAllCells()
                .filter(cell -> !((BuildableCellImpl) cell).isBuildable())
                .count();

        assertTrue(obstacleCount > 0, "There should be obstacles placed in the map.");
    }
}
