package it.unibo.the100dayswar.model.map.impl;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.map.api.Cell;
/**
 * Class that model the concept of a generic cell.
 */
public abstract class CellAbs implements Cell {

    private final int x;
    private final int y;
    private boolean isOccupied;

    /**
     * Constructor from coordinates.
     * @param coordinate coordinates to identify the cell in the map.
     */
    public CellAbs(final Position coordinate) {
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }
    /**
     * Constructor from a cell.
     * @param cell  identify the cell in the map.
     */
    public CellAbs(final Cell cell) {
        this.x = cell.getX();
        this.y = cell.getY();

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
       return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOccupation(final boolean occupation) {
        this.isOccupied = occupation;
    }
}
