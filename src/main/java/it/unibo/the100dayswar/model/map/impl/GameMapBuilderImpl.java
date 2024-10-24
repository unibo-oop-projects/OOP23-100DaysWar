package it.unibo.the100dayswar.model.map.impl;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.map.api.Cell;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;

import java.util.Random;
/**
 * The implementation of the gameBuilder.
 */
public class GameMapBuilderImpl implements GameMapBuilder {
    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final Random random = new Random();

    /**
     * The constructor of the class.
     * @param width is the width of the map.
     * @param height is the height of the map.
     */
    public GameMapBuilderImpl(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
    }

    /**
     * {@inheritDoc}
     * @return the initial map with all the cell setted as buildable.
     */
    @Override
    public GameMapBuilder initializeBuildableCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new BuildableCellImpl(new PositionImpl(x, y), true, false);
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     * @return a game map with the spawn's cells.
     */
    @Override
    public GameMapBuilder addSpawnCells() {
        final int spawn1 = random.nextInt(width);
        final int spawn2 = random.nextInt(width);
        grid[0][spawn1] = new BuildableCellImpl(new PositionImpl(0, spawn1), true, true);
        grid[height - 1][spawn2] = new BuildableCellImpl(new PositionImpl(width - 1, spawn2), true, true);
        return this;
    }

    /**
     * {@inheritDoc}
     * @return the map with the unbuildable cells.
     */
    @Override
    public GameMapBuilder addObstacles(final int numberOfObstacles) {
        int obstaclesAdded = 0;

        while (obstaclesAdded < numberOfObstacles) {
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);

            if (!grid[x][y].isSpawn()) { 
                grid[x][y] = new BuildableCellImpl(new PositionImpl(x, y), false, false);
                obstaclesAdded++;
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     * @return the map 
     */
    @Override
    public GameMap build() {
        return new GameMapImpl(width, height, grid);
    }
}
