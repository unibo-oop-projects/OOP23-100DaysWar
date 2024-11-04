package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.tower.api.TowerCreationParams;
import it.unibo.the100dayswar.model.tower.api.TowerType;

/**
 * Encapsulates the parameters necessary for creating a tower in the game. 
 * This class holds the details about the player who owns the tower, the cell 
 * where the tower will be placed, and the specific type of tower to be created.
 */
public class TowerCreationParamsImpl implements TowerCreationParams {
    private final Player player;
    private final Cell cell;
    private final TowerType towerType;

    /**
     * Constructs a new set of tower creation parameters.
     * 
     * @param player The player who will own the tower.
     * @param cell The cell location where the tower will be built.
     * @param towerType The type of tower to be constructed.
     */
    public TowerCreationParamsImpl(final Player player, final Cell cell, final TowerType towerType) {
        this.player = new PlayerImpl(player);
        this.cell = new CellImpl(cell);
        this.towerType = towerType;
    }

    /**
     * Returns the player who owns the tower.
     * 
     * @return The player associated with this set of tower creation parameters.
     */
    @Override
    public Player getPlayer() {
        return new PlayerImpl(this.player);
    }

    /**
     * Returns the cell where the tower will be built.
     * 
     * @return The cell associated with this set of tower creation parameters.
     */
    @Override
    public Cell getCell() {
        return new CellImpl(cell);
    }

    /**
     * Returns the type of tower to be constructed.
     * 
     * @return The tower type associated with this set of tower creation parameters.
     */
    @Override
    public TowerType getTowerType() {
        return towerType;
    }
}
