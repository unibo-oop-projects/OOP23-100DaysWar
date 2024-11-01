package it.unibo.the100dayswar.model.cell.impl;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
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

    /**
     * 
     * @param pos is the postion that will be checked.
     * @return true if the cell is in diagonal rather than the actusl cell.
     */
    private boolean isDiagonal(final Position pos) {
        final Position actualPos = this.getPosition();
        return (pos.getX() == actualPos.getX() - 1 && pos.getY() == actualPos.getY() - 1) 
                || (pos.getX() == actualPos.getX() + 1 && pos.getY() == actualPos.getY() + 1) 
                || (pos.getX() == actualPos.getX() - 1 && pos.getY() == actualPos.getY() + 1) 
                || (pos.getX() == actualPos.getX() + 1 && pos.getY() == actualPos.getY() - 1);
    }

    /**
     * @param pos is the postion that will be checked.
     * @return true if the cell is in cross rather than the actusl cell.
     */
    private boolean isCross(final Position pos) {
        final Position actualPos = this.getPosition();
        return (pos.getX() == actualPos.getX() && pos.getY() == actualPos.getY() - 1) 
                || (pos.getX() == actualPos.getX() && pos.getY() == actualPos.getY() + 1) 
                || (pos.getX() == actualPos.getX() - 1 && pos.getY() == actualPos.getY()) 
                || (pos.getX() == actualPos.getX() + 1 && pos.getY() == actualPos.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdiacent(final Cell cell) {
        final Position checkPos = cell.getPosition();
        return this.isCross(checkPos) || this.isDiagonal(checkPos);
    }
}
