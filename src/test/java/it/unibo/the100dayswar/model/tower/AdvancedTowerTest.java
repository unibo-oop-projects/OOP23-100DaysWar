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
import it.unibo.the100dayswar.model.tower.impl.AdvancedTowerImpl;
import it.unibo.the100dayswar.model.tower.impl.TowerFactoryImpl;

public class AdvancedTowerTest {
    final Cell mockCell = new CellImpl(new PositionImpl(0, 0), true, true);
    final Player mockPlayer = new PlayerImpl("MockPlayer", mockCell);
    final Tower mockAdvancedTower = new TowerFactoryImpl().buildTower(mockPlayer, TowerType.ADVANCED, mockCell);

    /**
     * Tests the initial properties of an AdvancedTower.
     */
    @Test
    void testAdvancedTowerProperties() {

        assertEquals(TowerType.ADVANCED, mockAdvancedTower.getTowerType(), "Tower type should be ADVANCED");
        assertEquals(60, mockAdvancedTower.currentHealth(), "Advanced tower health should match formula");
        assertEquals(10, mockAdvancedTower.getDamage(), "Advanced tower damage should match formula");
    }

    /**
     * Tests the upgrade functionality of an AdvancedTower.
     */
    @Test
    void testUpgradeAdvancedTower() {
        mockAdvancedTower.upgrade();
        assertEquals(2, mockAdvancedTower.getLevel(), "Tower level should increase after upgrade");
        assertEquals(120, mockAdvancedTower.currentHealth(), "Health should be updated after upgrade");
        assertEquals(20, mockAdvancedTower.getDamage(), "Damage should be updated after upgrade");
    }

    /**
     * Tests that a tower cannot exceed the maximum level.
     */
    @Test
    void testMaxLevelUpgrade() {
        for (int i = 0; i < 5; i++) {
            mockAdvancedTower.upgrade();
        }
        assertEquals(4, mockAdvancedTower.getLevel(), "Tower should not exceed MAX_LEVEL");
    }
}
