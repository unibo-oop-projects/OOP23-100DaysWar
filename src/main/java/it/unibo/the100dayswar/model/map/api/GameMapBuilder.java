package it.unibo.the100dayswar.model.map.api;

/**
 * Interface that model the builder of the game map.
 */
public interface GameMapBuilder {

    /**
     * Initialize the map with buildable cells.
     * @return The current instance of the builder.
     */
    GameMapBuilder initializeBuildableCells();

    /**
     * Add spawn cells in opposite positions of the map.
     * @return The current instance of the builder.
     */
    GameMapBuilder addSpawnCells();

    /**
     * Add a specified number of obstacle cells randomly on the map.
     * @param numberOfObstacles The number of obstacle cells to add.
     * @return The current instance of the builder.
     */
    GameMapBuilder addObstacles(int numberOfObstacles);

    /**
     * Complete the map construction and return the final game map.
     * @return The constructed GameMap.
     */
    GameMap build();
}
