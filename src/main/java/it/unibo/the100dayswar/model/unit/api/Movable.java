package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.model.map.api.Cell;

/**
 * An interface for the objects that can move around the map.
 */
public interface Movable {
    /**
     * Move the object to a new cell.
     * 
     * @param cell the new cell to move to
     * @throws IllegalStateException if the move is not allowed
     */
    void move(Cell cell);
    /**
     * Check if the object can move to a specific cell.
     * 
     * @param cell the cell to check
     * @return true if the object can move to the cell, false otherwise
     */
    boolean canMove(Cell cell);
}
