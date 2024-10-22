package it.unibo.the100dayswar.model.map.impl;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.map.api.BuildableCell;


/**
 * Class that model the concept of BuildableCell.
 */

public class BuildableCellImpl extends CellAbs  implements BuildableCell {

    private boolean isBuildable;
    private final boolean isSpawn;

    /**
     * Constructor from coordinates.
     * @param coordinate coordinates x,y.
     * @param isBuildable is true if cell is buildable.
     * @param isSpawn is true if is a spawn's cell.
     */
    public BuildableCellImpl(final Position coordinate, final boolean isBuildable, final boolean isSpawn) {
        super(coordinate);
        this.isBuildable = isBuildable;
        this.isSpawn = isSpawn;
    }

    /**
     * Constructor from coordinates.
     * @param cell is the cell that will be created.
     */
    public BuildableCellImpl(final BuildableCell cell){
        super(cell);
        this.isBuildable = cell.isBuildable();
        this.isSpawn = cell.isSpawn();

    }

   /**
    * {@inheritDoc}
    */
    @Override
    public boolean isBuildable() {
        return isBuildable;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void setState(final boolean isBuildable) {
        this.isBuildable = isBuildable;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public boolean isSpawn() {
        return this.isSpawn;
    }
}
