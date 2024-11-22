package it.unibo.the100dayswar.model.map.api;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.stream.Stream;


/**
 * Interface that model the game map.
 */
public interface GameMap extends Serializable {

    /**
     * Get the cell at the given coordinates.
     * @param position is the coordinate(x,y).
     * @return The cell at (x, y).
     */
    CellImpl getCell(Position position);

    /** 
     * 
     * Get the dimension of the map.
     * @return the size of the map.
     */
    Dimension getSize();

    /**
     * Get all the cells of the map.
     * @return a stream of all the cells.
     */
    Stream<CellImpl> getAllCells();
}
