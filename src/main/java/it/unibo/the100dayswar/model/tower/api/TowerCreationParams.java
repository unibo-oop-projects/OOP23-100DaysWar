package it.unibo.the100dayswar.model.tower.api;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;

/**
 * Interface that defines the contract for retrieving the creation parameters of a tower.
 */
public interface TowerCreationParams {
    /**
     * Get the player associated with the tower.
     * 
     * @return the player who will own the tower.
     */
    Player getPlayer();

    /**
     * Get the cell where the tower will be placed.
     * 
     * @return the cell that will house the tower.
     */
    Cell getCell();

    /**
     * Get the type of tower to be created.
     * 
     * @return the type of the tower.
     */
    TowerType getTowerType();
}
