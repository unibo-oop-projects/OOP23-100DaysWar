package it.unibo.the100dayswar.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.BonusCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.GameMapImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

class MapTest {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final String PLAYER_NAME = "Player1";
    private static final int BONUS_CELLS_COUNT = 3;
    private static final int OBSTACLE_COUNT = 5;
    private GameMapImpl gameMap;
    private MapManagerImpl mapManager;
    private Cell spawnCell;
    private Cell targetCell;
    private Cell bonusCell;

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
        spawnCell = gameMap.getAllCells()
                .filter(Cell::isSpawn)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No spawn cells found in the map."));
        targetCell = gameMap.getAllCells()
                .filter(cell -> cell.isAdiacent(spawnCell) && cell.isFree())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No non-spawn cells found in the map adiacent at the start cell."));
        bonusCell = gameMap.getAllCells()
                .filter(cell -> cell instanceof BonusCell)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No bonus cells found in the map."));
    }

    @Test
    void testGameMapInitialization() {
        assertNotNull(gameMap, "The game map should be initialized.");
        assertEquals(WIDTH, gameMap.getSize().getWidth(), "The map width should match.");
        assertEquals(HEIGHT, gameMap.getSize().getHeight(), "The map height should match.");

        final boolean hasSpawn = gameMap.getAllCells()
                .anyMatch(Cell::isSpawn);
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
        final Player player1  = new PlayerImpl(PLAYER_NAME, spawnCell);
        final Soldier soldier1 = new SoldierImpl(player1);

        mapManager.update(new Pair<>(soldier1, spawnCell));

        final Set<Cell> playerCells = mapManager.getPlayersCells().get(player1);
        assertTrue(spawnCell.getUnit().isPresent(), "The spawn cell should contain the soldier.");
        assertTrue(spawnCell.isSpawn(), "The spawn cell should be a spawn cell.");
        assertNotNull(playerCells, "The player should own some cells.");
        assertTrue(playerCells.contains(spawnCell), "The player should own their spawn cell.");
    }

    @Test
    void testBonusCellActivation() {
        final Cell baseCell = bonusCell;
        final PlayerImpl player1 = new PlayerImpl(PLAYER_NAME, baseCell);
        final SoldierImpl soldier = new SoldierImpl(player1);

        gameMap.setOccupationOnCell(baseCell, Optional.of(soldier));
        ((BonusCell) bonusCell).notify(player1);

        assertFalse(((BonusCell) bonusCell).isBonusActive(), "The bonus cell should be inactive after activation.");
    }

    @Test
    void testSoldierMovement() {
        final Player player1 = new PlayerImpl(PLAYER_NAME, spawnCell);
        final Soldier soldier1 = new SoldierImpl(player1);

        mapManager.update(new Pair<>(soldier1, spawnCell));

        assertTrue(spawnCell.getUnit().isPresent(), "The start cell should contain the soldier.");
        assertFalse(targetCell.getUnit().isPresent(), "The target cell should not contain the soldier.");

        mapManager.update(new Pair<>(soldier1, targetCell));

        assertTrue(targetCell.getUnit().isPresent(), "The target cell should now contain the soldier.");
        assertFalse(spawnCell.getUnit().isPresent(), "The start cell should no longer contain the soldier.");
    }

    @Test
    void testSpawnCellOccupiedByNewSoldier() {
        final Player player1 = new PlayerImpl(PLAYER_NAME, spawnCell);
        final Soldier soldier1 = new SoldierImpl(player1);
        final Soldier soldier2 = new SoldierImpl(player1);
        mapManager.update(new Pair<>(soldier1, spawnCell));

    assertTrue(spawnCell.isSpawn(), "The spawn cell should is a spawnCell.");
    assertTrue(spawnCell.getUnit().isPresent(), "The spawn cell should contain the first soldier.");

    final Exception exception = assertThrows(IllegalStateException.class, () -> {
        mapManager.update(new Pair<>(soldier2, spawnCell));
    });

    assertEquals("Spawn cell is occupied. Move the existing soldier.", exception.getMessage());
    assertTrue(spawnCell.getUnit().isPresent(), "The spawn cell should still contain the first soldier.");
}
    @Test
    void testObstaclePlacement() {
        final long obstacleCount = gameMap.getAllCells()
                .filter(cell -> !cell.isBuildable())
                .count();

        assertTrue(obstacleCount > 0, "There should be obstacles placed in the map.");
    }
}
