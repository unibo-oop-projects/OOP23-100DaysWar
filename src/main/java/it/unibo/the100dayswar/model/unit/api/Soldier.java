package it.unibo.the100dayswar.model.unit.api;


import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * Interface that defines a soldier unit.
 */
public interface Soldier extends Unit, Movable {
     /**
     * Notifies all observers of this Soldier that it has moved to a new cell.
     * 
     * @param target the cell the Soldier has moved to
     */
    void notifyObservers(Cell target);
}
