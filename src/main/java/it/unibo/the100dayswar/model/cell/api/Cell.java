package it.unibo.the100dayswar.model.cell.api;

import java.io.Serializable;

import it.unibo.the100dayswar.commons.utilities.api.Position;

/**
 * Interface that model a cell.
 */
public interface Cell extends Serializable {
    /**
     * 
     * @return the position of the cell in the map
    */
    Position getPosition();

    /**
     * @return true if the cell is not occupied by a soldier or a tower and is Buildable.
     */
    boolean isFree();

    /**
     * @return true if is a spawn cell;
     */
    boolean isSpawn();

     /**
     * @return true if the cell in param is adiacent to the actual cell.
     * @param cell il the cell that will be checked.
     */
    boolean isAdiacent(Cell cell);
}
