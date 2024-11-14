package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.model.cell.api.Cell;

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
    public void performAttack();
    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final Cell cell);
    /**
     * {@inheritDoc}
     */
    @Override
    public void movementRequest(final Cell target);
    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getPosition();
}
