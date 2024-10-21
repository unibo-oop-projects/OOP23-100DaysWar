package it.unibo.the100dayswar.model.player.api;

import java.util.Set;

import it.unibo.the100dayswar.model.map.api.Cell;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Interface that defines the behavior of the possible moves that can be performed by a player.
 * A player can buy units, move them, upgrade them, and engage in combat.
 */
public interface Player {
    /**
     * Buys a unit, such as a soldier or tower, and deducts the cost from the player's bank account.
     *
     * @param unit the unit to buy
     * @throws IllegalStateException if the player does not have enough resources
     */
    void buyUnit(Unit unit);
    /**
     * Upgrades a given unit to increase its power, at the cost of resources.
     *
     * @param unit the unit to upgrade
     * @throws IllegalStateException if the player does not have enough resources to upgrade
     */
    void upgradeUnit(Unit unit);
    /**
     * Moves a unit from one cell to another on the game map.
     *
     * @param unit the unit to move
     * @throws IllegalArgumentException if the move is not allowed
     */
    void moveUnit(Movable unit);
    /**
     * Adds a unit to the player's army.
     *
     * @param unit the unit to add
     */
    void addUnit(Unit unit);
    /**
     * Removes a unit from the player's army.
     *
     * @param unit the unit to remove
     */
    void removeUnit(Unit unit);
    /**
     * Returns the spawn point of player's units.
     *
     * @return the spawn point of the player
     */
    Cell getSpawnPoint();
    /**
     * Returns the player's name.
     *
     * @return the username of the player
     */
    String getUsername();
    /**
     * Adds resources to the player's bank account.
     *
     * @param amount the amount of resources to add
     */
    void earnResources(int amount);
    /**
     * The units owned by the player.
     * 
     * @return the units owned by the player
     */
    Set<Unit> getUnits();
    /**
     * The soldiers owned by the player.
     * 
     * @return the soldiers owned by the player
     */
    Set<Unit> getSoldiers();
    /**
     * Adds resources to the player's bank account.
     *
     * @param amount the amount of resources to add
     */
    void addResources(int amount);
}
