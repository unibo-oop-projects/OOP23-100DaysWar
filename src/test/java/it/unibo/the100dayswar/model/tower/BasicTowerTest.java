package it.unibo.the100dayswar.model.tower;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.tower.impl.TowerFactoryImpl;

public class BasicTowerTest {
    final Cell mockCell = new CellImpl(new PositionImpl(0, 0), true, true);
    final Player mockPlayer = new PlayerImpl("MockPlayer", mockCell);
    final Tower mockBasicTower = new TowerFactoryImpl().buildTower(mockPlayer, TowerType.BASIC, mockCell);

    /**
     * Tests the initial properties of a BasicTower.
     */
    @Test
    void testBasicTowerProperties() {
        assertEquals(TowerType.BASIC, mockBasicTower.getTowerType(), "Tower type should be BASIC");
        assertEquals(30, mockBasicTower.currentHealth(), "Basic tower health should match formula");
        assertEquals(6, mockBasicTower.getDamage(), "Basic tower damage should match formula");
    }

     /**
     * Tests the upgrade functionality of a BasicTower.
     */
    @Test
    void testUpgradeBasicTower() {
        mockBasicTower.upgrade();
        assertEquals(2, mockBasicTower.getLevel(), "Tower level should increase after upgrade");
        assertEquals(60, mockBasicTower.currentHealth(), "Health should be updated after upgrade");
        assertEquals(12, mockBasicTower.getDamage(), "Damage should be updated after upgrade");
    }

    /**
     * Tests that a tower cannot exceed the maximum level.
     */
    @Test
    void testMaxLevelUpgrade() {
        for (int i = 0; i < 5; i++) {
            mockBasicTower.upgrade();
        }
        assertEquals(4, mockBasicTower.getLevel(), "Tower should not exceed MAX_LEVEL");
    }
}
