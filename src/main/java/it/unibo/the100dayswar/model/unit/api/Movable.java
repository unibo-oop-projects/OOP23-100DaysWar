package it.unibo.the100dayswar.model.unit.api;

/**
 * An interface for the objects that can move around the map.
 */
public interface Movable {
    /**
     * Move the object to a new cell.
     * 
     * @throws IllegalStateException if the move is not allowed
     */
    void move();
}
