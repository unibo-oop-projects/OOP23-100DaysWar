package it.unibo.the100dayswar.model.map.api;
/**
 * Interface that model a cell.
 */

import it.unibo.the100dayswar.commons.utilities.api.Position;

public interface Cell {
    /**
     * 
     * @return the position of the cell in the map
    */
    Position getPosition();

    /**
     * @return true if the cell il occupied by a soldier or a tower.
     */
    boolean isFree();

    /**
     * @return true if is a spawn cell;
     */
    boolean isSpawn();
}
