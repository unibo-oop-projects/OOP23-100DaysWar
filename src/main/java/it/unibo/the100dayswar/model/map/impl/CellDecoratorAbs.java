package it.unibo.the100dayswar.model.map.impl;

import it.unibo.the100dayswar.model.map.api.Cell;
import it.unibo.the100dayswar.model.map.api.CellDecorator;
/**
 * abstract class that implements a generic decorator.
 */
public abstract class CellDecoratorAbs implements CellDecorator {

    private final  BuildableCellImpl decoratedCell;
    /**
     * the constructor of the class.
     * @param decoratedCell is the decorated cell.
     */
    public CellDecoratorAbs(final BuildableCellImpl decoratedCell) {
        this.decoratedCell = new BuildableCellImpl(decoratedCell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getDecoratedCell() {
        return new BuildableCellImpl(this.decoratedCell);
    }
}
