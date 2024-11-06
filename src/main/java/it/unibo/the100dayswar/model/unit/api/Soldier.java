package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.commons.patterns.Observable;
import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * Interface that defines a soldier unit.
 */
public interface Soldier extends Unit, Movable, Observable<Pair<Soldier, Cell>> {
    /**
     * Requests the movement of the soldier to the specified target cell.
     *
     * @param target the cell to which the soldier is requested to move
     */
    void movementRequest(Cell target);
    /** 
     * Attaches an observer to this Soldier. The observer will be notified of changes
     * to the Soldier's state, specifically when the Soldier moves to a new cell.
     *
     * @param observer the observer to attach, which will receive updates in the form
     * of a Pair containing the Soldier and the Cell it moves to
     */
    @Override
    void attach(Observer<Pair<Soldier, Cell>> observer);
    /**
     * Detaches an observer from this Soldier.
     *
     * @param observer the observer to detach
     */
    @Override
    void detach(Observer<Pair<Soldier, Cell>> observer);
    /**
     * Notifies all observers of this Soldier that it has moved to a new cell.
     * 
     * @param target the cell the Soldier has moved to
     */
    void notifyObservers(Cell target);
}
