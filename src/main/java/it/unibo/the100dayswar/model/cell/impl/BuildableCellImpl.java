package it.unibo.the100dayswar.model.cell.impl;

import java.util.Optional;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.unit.api.Unit;


/**
 * Class that model the concept of BuildableCell.
 */

public class BuildableCellImpl extends CellAbs  implements BuildableCell {

    private final boolean isBuildable;
    private final boolean isSpawn;
    private  Optional<Unit> currentUnit;

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
        this.currentUnit = Optional.empty();
    }

    /**
     * Constructor from coordinates.
     * @param cell is the cell that will be created.
     */
    public BuildableCellImpl(final BuildableCell cell) {
        super(cell);
        this.isBuildable = cell.isBuildable();
        this.isSpawn = cell.isSpawn();
        this.currentUnit = Optional.empty();
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
    public boolean isSpawn() {
        return this.isSpawn;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public Optional<Unit> getUnit() {
        return Optional.of(this.currentUnit.get());
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public boolean isFree() {
        return this.getUnit().isEmpty() && this.isBuildable();
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void setOccupation(final Optional<Unit> unit) {
       this.currentUnit = unit;
    }
}
