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
     * The current cell of the object.
     * 
     * @return the current cell
     */
    Cell getCell();
}
