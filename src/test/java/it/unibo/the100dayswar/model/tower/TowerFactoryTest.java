package it.unibo.the100dayswar.model.tower;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.tower.impl.TowerFactoryImpl;
import it.unibo.the100dayswar.model.tower.impl.BasicTowerImpl;
import it.unibo.the100dayswar.model.tower.impl.AdvancedTowerImpl;

/**
 * Test suite for the TowerFactoryImpl class.
 */
class TowerFactoryTest {
    private final Cell mockCell = new CellImpl(new PositionImpl(0, 0), true, true);
    private final Player mockPlayer = new PlayerImpl("MockPlayer", mockCell);

    /**
     * Tests the creation of a BasicTower using the TowerFactory.
     */
    @Test
    void testCreateBasicTower() {
        final TowerFactoryImpl factory = new TowerFactoryImpl();

        final var tower = factory.buildTower(mockPlayer, TowerType.BASIC, mockCell);
        assertTrue(tower instanceof BasicTowerImpl, "Tower should be of type BasicTowerImpl");
        assertEquals(TowerType.BASIC, tower.getTowerType(), "Tower type should be BASIC");
    }

    /**
     * Tests the creation of an AdvancedTower using the TowerFactory.
     */
    @Test
    void testCreateAdvancedTower() {
        final TowerFactoryImpl factory = new TowerFactoryImpl();

        final var tower = factory.buildTower(mockPlayer, TowerType.ADVANCED, mockCell);
        assertTrue(tower instanceof AdvancedTowerImpl, "Tower should be of type AdvancedTowerImpl");
        assertEquals(TowerType.ADVANCED, tower.getTowerType(), "Tower type should be ADVANCED");
    }

    /**
     * Tests that an exception is thrown for an invalid tower type.
     */
    @Test
    void testInvalidTowerType() {
        final TowerFactoryImpl factory = new TowerFactoryImpl();

        final Exception exception1 = assertThrows(IllegalArgumentException.class, () -> 
            factory.buildTower(null, TowerType.BASIC, mockCell)
        );
        assertTrue(exception1.getMessage().contains("All arguments must be non null"));

        final Exception exception2 = assertThrows(IllegalArgumentException.class, () -> 
            factory.buildTower(mockPlayer, null, mockCell)
        );
        assertTrue(exception2.getMessage().contains("All arguments must be non null"));

        final Exception exception3 = assertThrows(IllegalArgumentException.class, () -> 
            factory.buildTower(mockPlayer, TowerType.BASIC, null)
        );
        assertTrue(exception3.getMessage().contains("All arguments must be non null"));
    }
}
