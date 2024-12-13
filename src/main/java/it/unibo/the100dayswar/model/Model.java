package it.unibo.the100dayswar.model;

import it.unibo.the100dayswar.model.cell.api.Cell;

/** 
 * The interface of the model of the game.
 */
public interface Model {
    
    /**
     * Buy a basic tower.
     * @param position the position of the tower
     */
    void buyBasicTower(Cell position);

    /**
     * Buy an advanced tower.
     * @param position the position of the tower
     */
    void buyAdvancedTower(Cell position);

    /**
     * Buy a soldier.
     */
    void buySoldier();

    /**
     * Add a player to the game.
     * @param player the name of the player
     */
    void addPlayer(String player);
}
