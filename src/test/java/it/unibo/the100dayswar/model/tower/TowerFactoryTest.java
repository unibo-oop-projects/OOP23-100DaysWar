package it.unibo.the100dayswar.model.tower;

import static org.junit.jupiter.api.Assertions.*;
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
public class TowerFactoryTest {

    /**
     * Tests the creation of a BasicTower using the TowerFactory.
     */
    @Test
    void testCreateBasicTower() {
        TowerFactoryImpl factory = new TowerFactoryImpl();
        Cell mockCell = new CellImpl(new PositionImpl(0, 0), true, true);
        Player mockPlayer = new PlayerImpl("P1");
        Cell mockCell = new CellImpl(null, false, false)

        var tower = factory.buildTower(mockPlayer, TowerType.BASIC, mockCell);
        assertTrue(tower instanceof BasicTowerImpl, "Tower should be of type BasicTowerImpl");
        assertEquals(TowerType.BASIC, tower.getTowerType(), "Tower type should be BASIC");
    }

    /**
     * Tests the creation of an AdvancedTower using the TowerFactory.
     */
    @Test
    void testCreateAdvancedTower() {
        TowerFactoryImpl factory = new TowerFactoryImpl();
        Player mockPlayer = Mockito.mock(Player.class);
        Cell mockCell = Mockito.mock(Cell.class);

        var tower = factory.buildTower(mockPlayer, TowerType.ADVANCED, mockCell);
        assertTrue(tower instanceof AdvancedTowerImpl, "Tower should be of type AdvancedTowerImpl");
        assertEquals(TowerType.ADVANCED, tower.getTowerType(), "Tower type should be ADVANCED");
    }

    /**
     * Tests that an exception is thrown for an invalid tower type.
     */
    @Test
    void testInvalidTowerType() {
        TowerFactoryImpl factory = new TowerFactoryImpl();
        Player mockPlayer = Mockito.mock(Player.class);
        Cell mockCell = Mockito.mock(Cell.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            factory.buildTower(mockPlayer, null, mockCell)
        );
        assertTrue(exception.getMessage().contains("Unknown Tower Type"));
    }
}
