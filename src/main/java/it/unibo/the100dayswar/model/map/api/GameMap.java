package it.unibo.the100dayswar.model.map.api;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.cell.api.Cell;

import java.awt.Dimension;


/**
 * Interface that model the game map.
 */
public interface GameMap {

    /**
     * Get the cell at the given coordinates.
     * @param position is the coordinate(x,y).
     * @return The cell at (x, y).
     */
    Cell getCell(Position position);

    /** 
     * 
     * Get the dimension of the map.
     * @return the size of the map.
     */
    Dimension getSize();
}
