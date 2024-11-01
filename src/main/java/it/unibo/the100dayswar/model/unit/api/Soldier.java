package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * Interface that defines a soldier unit.
 */
public interface Soldier extends Unit, Movable {
    /**
     * Check if the object can move to a specific cell.
     * 
     * @param target the cell to check
     * @return true if the object can move to the cell, false otherwise
     */
    boolean canMove(Cell target);
}
