package it.unibo.the100dayswar.model.soldier;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    private Soldier soldier;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        soldier = new SoldierImpl(testPlayer);
        testPlayer = new PlayerImpl("test", 
            new BuildableCellImpl(new PositionImpl(1, 1), 
            true, 
            true));
    }

    @Test
    void testInitialization() {
        assertEquals(testPlayer.getSpawnPoint(), soldier.getPosition());
        assertEquals(testPlayer, soldier.getOwner());
    }

    @Test
    void testMove() {
        final Cell testCell = new BuildableCellImpl(new PositionImpl(2, 2), true, true);
        soldier.move(testCell);
        assertEquals(testCell, soldier.getPosition());
    }

    @Test
    void testPerformAttack() {
        final Combatant target = new SoldierImpl(testPlayer);
        soldier.performAttack(target);
        assertEquals(100, target.currentHealth());
    }

    @Test
    void testMovementRequest() {
    }
}
