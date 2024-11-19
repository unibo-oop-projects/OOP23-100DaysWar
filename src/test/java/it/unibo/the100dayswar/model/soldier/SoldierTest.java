package it.unibo.the100dayswar.model.soldier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.unit.api.Combatant;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.BuildableCellImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;

class SoldierTest {

    
    private Player testPlayer;
    private Soldier soldier;

    @BeforeEach
    void setUp() {
        testPlayer = new PlayerImpl("testPlayer", 
            new BuildableCellImpl(new PositionImpl(1, 1), 
            true, 
            true));
        soldier = new SoldierImpl(testPlayer);
    }

    @Test
    void testInitialization() {
        assertTrue(testPlayer.getUsername().equals(soldier.getOwner().getUsername()), "The soldier should be owned by the player");
        assertTrue(testPlayer.getSpawnPoint().getPosition().equals(soldier.getPosition().getPosition()), "The soldier should be spawned in the player's spawn point");
    }

    @Test
    void testMove() {
        final Cell testCell = new BuildableCellImpl(new PositionImpl(2, 2), true, true);
        soldier.move(testCell);
        assertEquals(testCell, soldier.getPosition());
    }

    @Test
<<<<<<< HEAD
    void testPerformAttack() {
        final Combatant target = new SoldierImpl(testPlayer);
        soldier.performAttack(target);
        assertEquals(100, target.currentHealth());
    }

    @Test
    void testMovementRequest() {
=======
    void testUpgrade() {
        assertEquals(1, soldier.getLevel());
        assertEquals(100, soldier.currentHealth());
        soldier.upgrade();
        assertEquals(2, soldier.getLevel());
        assertEquals(150, soldier.currentHealth());
    }

    @Test
    void testPerformAttack() {
        final Combatant target = new SoldierImpl(testPlayer);
        assertEquals(100, soldier.currentHealth());
        assertEquals(100, target.currentHealth());
        soldier.performAttack(target);
        assertTrue(soldier.currentHealth() == 100 && target.currentHealth() == 0 
            || soldier.currentHealth() == 0 && target.currentHealth() == 100 );
>>>>>>> 0cf7697 (completed the test of the soldier and fixed some errors)
    }
}
