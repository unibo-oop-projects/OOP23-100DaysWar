package it.unibo.the100dayswar.controller.mapcontroller.api;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * Interface that models the map controller.
 */
public interface MapController {

    /**
    * @return the height of the map.
    */
    Integer getMapHeight();

    /**
     * @return the width of the map.
     */ 
    Integer getMapWidth();

    /**
     * @return the map.
     */
    Cell[][] getMap();

    /**
     * @return the number of bonus cells of the map.
     */
    Integer getBonusCells();

    /**
     * @return the number of obstacles of the map.
     */
    Integer getObstacles(); 

    /**
     * @return the cell at the given position.
     * @param x the x coordinate of the cell.
     * @param y the y coordinate of the cell.
     */
    Cell getCellAtPosition(Position pos);


}
