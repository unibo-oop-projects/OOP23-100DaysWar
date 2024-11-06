package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * An interface for the objects that can move around the map.
 */
public interface Movable {
    /**
     * This method helpa the movement of an object to a specified cell
     * to connect it to a manager that will check and handle it in 
     * case of a valid movement.
     *
     * @param target the cell that is requested to move to
     */
    void movementRequest(Cell target);
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
     * @param target the cell to check
     * @return true if the object can move to the cell, false otherwise
     */
    boolean canMove(Cell target);
}
