package it.unibo.the100dayswar.model.tower.api;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Interface that rapresent a generic tower.
 */
public interface Tower extends Unit {
    /**
     * Method that returns the damage that the tower inflicts.
     * to another unit. 
     * 
     * @return the damage
     */
    int getDamage();

    /**
     * Method that return the type of the tower.
     * 
     * @return the tower type
     */
    TowerType getTowerType();

    /**
     * Method that return the owner of the tower.
     * 
     * @return the owner of the tower
     */
    Player getOwner();

    /**
     * Method that return the position of the tower.
     * 
     * @return the position of the tower
     */
    Cell getPosition();
}
