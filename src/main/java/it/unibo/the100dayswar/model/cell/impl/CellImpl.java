package it.unibo.the100dayswar.model.cell.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * Implementation of a cell.
 */
public class CellImpl extends CellAbs {

    /**
     * Constructor for copy a given cell.
     * 
     * @param cell cell to copy
     */
    public CellImpl(final Cell cell) {
        super(cell);
        //TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFree'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSpawn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSpawn'");
    }
}
