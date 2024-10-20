package it.unibo.the100dayswar.model.map.impl;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.map.api.BuildableCell;


/**
 * Class that model the concept of BuildableCell.
 */

public class BuildableCellImpl extends CellAbs  implements BuildableCell {

    private boolean isBuildable;

    /**
     * Constructor from coordinates.
     * @param coordinate coordinates x,y.
     * @param isBuildable is true if cell is buildable.
     */
    public BuildableCellImpl(final Position coordinate, final boolean isBuildable) {
        super(coordinate);
        this.isBuildable = isBuildable;
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
}
