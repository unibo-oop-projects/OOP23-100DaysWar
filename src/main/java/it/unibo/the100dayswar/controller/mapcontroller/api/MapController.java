package it.unibo.the100dayswar.controller.mapcontroller.api;

import java.util.List;

import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.view.map.CellView;

public interface MapController {
    /**
     * Gets the width of the map.
     *
     * @return the width of the map in cells
     */
    int getMapWidth();

    /**
     * Gets the height of the map.
     *
     * @return the height of the map in cells
     */
    int getMapHeight();

    /**
     * Retrieves a list of CellView objects for rendering the map.
     *
     * @return a list of CellView objects
     */
    List<CellView> getCellsView();

    /**
     * Gets the game map.
     *
     * @return the game map
     */
    GameMap getMap();
}