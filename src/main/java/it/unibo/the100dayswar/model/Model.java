package it.unibo.the100dayswar.model;

import it.unibo.the100dayswar.commons.utilities.impl.Direction;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;

/** 
 * The interface of the model of the game.
 */
public interface Model {

    /**
     * Buy a basic tower.
     * 
     * @param position the position of the tower
     */
    void buyBasicTower(Cell position);

    /**
     * Buy an advanced tower.
     * 
     * @param position the position of the tower
     */
    void buyAdvancedTower(Cell position);

    /**
     * Buy a soldier.
     */
    void buySoldier();

    /**
     * Add a player to the game.
     * 
     * @param username the name of the player
     */
    void addPlayer(String username);

    /**
     * Move a player following the direction.
     * 
     * @param player the player to move
     * @param direction the direction to follow
     */
    void movePlayer(Player player, Direction direction);

    /**
     * Save the current game.
     * 
     * @param path the path of the saved file
     * 
     * @return true if the game is saved correctly
     */
    boolean saveGame(String path);

    /**
     * Upgrade the soldier.
     * 
     * @param soldier the soldier to upgrade
     */
    void upgradeSoldier(Soldier soldier);

    /**
     * Skip the current turn.
     */
    void skipTurn();
}
