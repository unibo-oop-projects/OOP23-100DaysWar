package it.unibo.the100dayswar.model.soldier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.impl.AdvancedTowerImpl;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;

class SoldierTest {

    private Player testPlayer;
    private Soldier soldier;

    @BeforeEach
    void setUp() {
        testPlayer = new HumanPlayerImpl("testPlayer", 
            new CellImpl(new PositionImpl(1, 1), 
            true, 
            true));
        soldier = new SoldierImpl(testPlayer);
    }

    @Test
    void testInitialization() {
        assertEquals(testPlayer.getUsername(), soldier.getOwner().getUsername());
        assertEquals(testPlayer.getSpawnPoint().getPosition(), soldier.getPosition().getPosition());
        final Cell testCell = new CellImpl(new PositionImpl(2, 2), true, true);
        soldier.move(testCell);
        assertEquals(testCell, soldier.getPosition());
    }

    @Test
    void testUpgrade() {
        final int healthAfterUpgrade = 125;
        assertEquals(1, soldier.getLevel());
        assertEquals(100, soldier.currentHealth());
        soldier.upgrade();
        assertEquals(2, soldier.getLevel());
        assertEquals(healthAfterUpgrade, soldier.currentHealth());
    }

    @Test
    void testAttackSoldier() {
        final Soldier target = new SoldierImpl(testPlayer);
        assertEquals(100, soldier.currentHealth());
        assertEquals(100, target.currentHealth());
        soldier.performAttack(target);
        assertTrue(
            soldier.currentHealth() == 100 && target.currentHealth() == 0 
            ||
            soldier.currentHealth() == 0 && target.currentHealth() == 100
        );
    }

    @Test
    void testAttackSoldierWithDifferentLevel() {
        final Soldier target = new SoldierImpl(testPlayer);
        final int soldierHealth = 100;
        final int targetHealth = 175;
        target.upgrade();
        target.upgrade();
        assertEquals(100, soldier.currentHealth());
        assertEquals(targetHealth, target.currentHealth());
        soldier.performAttack(target);
        assertTrue(
            soldier.currentHealth() == soldierHealth && target.currentHealth() == 0
            ||
            soldier.currentHealth() == 0 && target.currentHealth() == targetHealth
        );
    }

    @Test
    void testAttackTower() {
        final Tower tower = new AdvancedTowerImpl(testPlayer, new CellImpl(new PositionImpl(1, 1), true, true));
        final int towerHealth = tower.currentHealth();
        final int defSoldierDamage = 30;
        assertEquals(100, soldier.currentHealth());
        soldier.performAttack(tower);
        assertTrue(
            soldier.currentHealth() == 100 && tower.currentHealth() == towerHealth - defSoldierDamage * soldier.getLevel()
        );
    }
}
