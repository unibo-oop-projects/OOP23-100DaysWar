package it.unibo.the100dayswar.model.map.api;
/**
 * Interface that represents an object that can decorates a cell.
 */
public interface CellDecorator {
    /**
     * method that return the decorated cell.
     * 
     * @return the decorated cell.
     */
    Cell getDecoratedCell();
}
