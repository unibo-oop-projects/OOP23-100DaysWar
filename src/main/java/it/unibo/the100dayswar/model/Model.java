package it.unibo.the100dayswar.model;

import it.unibo.the100dayswar.commons.utilities.impl.Direction;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.unit.api.Unit;

/** 
 * The interface of the model of the game.
 */
public interface Model {
    /**
     * Buy a tower of the specified type.
     * 
     * @param type the type of the tower
     * @param position the position of the tower
     */
    void buyTower(TowerType type, Cell position);

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
     * Move a soldier following the specified direction.
     * 
     * @param direction the direction to follow
     * @return true if the soldier was moved correctly false otherwise
     */
    boolean moveSoldier(Soldier soldier, Direction direction);

    /**
     * Save the current game.
     * 
     * @param path the path of the saved file
     * 
     * @return true if the game is saved correctly
     */
    boolean saveGame(String path);

    /**
     * Upgrade the unit.
     * 
     * @param Unit the unit to upgrade
     */
    void upgradeUnit(Unit unit);

    /**
     * Skip the current turn.
     */
    void skipTurn();

    /*
     * Pause the game.
     */
    void pauseGame();

    /**
     * Reasume the game.
     */
    void reasumeGame();
}
