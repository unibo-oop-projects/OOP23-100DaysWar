package it.unibo.the100dayswar.model.map.impl;

import java.awt.Dimension;
import java.util.Optional;
import java.util.Arrays;


import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.api.GameMap;


/**
 * The implementation of the map.
 */
public class GameMapImpl implements GameMap {

    private final Dimension size;
    private final Cell[][] map;

    /**
     * The constructor of the map.
     * @param width is the width.
     * @param height is the height.
     * @param grid is the matrix of Cells.
     */
    public GameMapImpl(final int width, final int height, final Cell[][] grid) {
        this.size = new Dimension(width, height);
        this.map = new Cell[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            this.map[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
    }

    /**
     * {@inheritDoc}
     * @param position is the position (x,y) of the cell.
     * @return the Cell in that specific position or error if the position in not valid.
     */
    @Override
    public Cell getCell(final Position position) {
        return getCellByPosition(position).orElseThrow(() -> new IllegalArgumentException("Invalid position: " + position));
    }

    /**
     * {@inheritDoc}
     * @return the size of the game map.
     */
    @Override
    public Dimension getSize() {
        return new Dimension(this.size);
    }
    /**
     * ausiliar funcion for getCell.
     * @param position is the postion (x,y) of the cell.
     * @return the cell if in that position exisit a cell.
     */
    private Optional<Cell> getCellByPosition(final Position position) {
        if (!isInMap(position)) {
            return Optional.empty();
        }
        return Optional.of(map[position.getX()][position.getY()]);
    }

    /**
     * ausiliar funcion for getCellByPosition.
     * @param pos is a position.
     * @return true if the position is in the map.
     */
    private boolean isInMap(final Position pos) {
        return pos.getX() >= 0 && pos.getX() < size.getWidth() && pos.getY() >= 0 && pos.getY() < size.getHeight();
    }
}
