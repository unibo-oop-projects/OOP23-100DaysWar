package it.unibo.the100dayswar.model.cell.impl;

import java.util.Optional;
import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Implements the BuildableCell interface representing a cell that can potentially host a unit.
 * This class provides mechanisms to check buildability and spawn capability, as well as to manage occupancy.
 */
public class BuildableCellImpl extends CellAbs implements BuildableCell {
    private static final long serialVersionUID = 1L;

    private final boolean isBuildable;
    private final boolean isSpawn;
    private transient Unit currentUnit;

    /**
     * Constructs a BuildableCell with specified characteristics.
     *
     * @param coordinate The position of the cell on the grid
     * @param isBuildable Indicates whether the cell can be built upon
     * @param isSpawn Indicates whether the cell is a spawn point
     */
    public BuildableCellImpl(final Position coordinate, final boolean isBuildable, final boolean isSpawn) {
        super(coordinate);
        this.isBuildable = isBuildable;
        this.isSpawn = isSpawn;
        this.currentUnit = null;
    }

    /**
     * Copy constructor that creates a new instance based on the properties of another BuildableCell.
     *
     * @param cell The cell to copy properties from
     */
    public BuildableCellImpl(final BuildableCell cell) {
        super(cell);
        this.isBuildable = cell.isBuildable();
        this.isSpawn = cell.isSpawn();
        this.currentUnit = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBuildable() {
        return this.isBuildable;
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
        return Optional.ofNullable(this.currentUnit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFree() {
        return this.currentUnit == null && this.isBuildable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOccupation(final Optional<Unit> unit) {
        this.currentUnit = unit.orElse(null);
    }
}
