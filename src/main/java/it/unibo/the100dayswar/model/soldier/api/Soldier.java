package it.unibo.the100dayswar.model.soldier.api;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Interface that defines a soldier unit which can attack another
 * Combatant object and can move around the cells.
 */
public interface Soldier extends Unit, Movable {
    /**
     * The cost to purchase a soldier.
     */
    int DEFAULT_COST = 50;
    /**
     * {@inheritDoc}
     */
    @Override
    void performAttack();
    /**
     * {@inheritDoc}
     */
    @Override
    void move(Cell cell);
    /**
     * {@inheritDoc}
     */
    @Override
    void movementRequest(Cell target);
    /**
     * {@inheritDoc}
     */
    @Override
    Cell getPosition();
}
