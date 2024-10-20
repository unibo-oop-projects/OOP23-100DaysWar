package it.unibo.the100dayswar.model.map.api;

/**
 * Interface a model a  BuildableCell where you can build a tower o put a soldier down.
 */

public interface BuildableCell extends Cell {
    /**
     * Cell state getter.
     * @return true if the cell is buildable.
     */
    boolean isBuildable();

    /**
     * Cell state setter.
     * @param isBuildable is true if the cell is buildable.
     */
    void setState(boolean isBuildable);
}
