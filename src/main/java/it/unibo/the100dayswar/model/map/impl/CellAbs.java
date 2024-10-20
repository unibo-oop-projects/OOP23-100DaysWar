package it.unibo.the100dayswar.model.map.impl;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.map.api.Cell;
/**
 * Class that model the concept of a generic cell.
 */
public abstract class CellAbs implements Cell {

    private final int x;
    private final int y;

    /**
     * Constructor from coordinates.
     * @param coordinate coordinates to identify the cell in the map.
     */
    public CellAbs(final Position coordinate) {
        this.x = coordinate.getX();
        this.y = coordinate.getY();
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
}
