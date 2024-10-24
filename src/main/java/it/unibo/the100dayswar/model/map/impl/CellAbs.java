package it.unibo.the100dayswar.model.map.impl;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.map.api.Cell;
/**
 * Class that model the concept of a generic cell.
 */
public abstract class CellAbs implements Cell {

    private final Position position;

    /**
     * Constructor from coordinates.
     * @param coordinate coordinates to identify the cell in the map.
     */
    public CellAbs(final Position coordinate) {
        this.position = new PositionImpl(coordinate);
    }
    /**
     * Constructor from a cell.
     * @param cell  identify the cell in the map.
     */
    public CellAbs(final Cell cell) {
        this.position = cell.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return new PositionImpl(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isFree();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isSpawn();
}
