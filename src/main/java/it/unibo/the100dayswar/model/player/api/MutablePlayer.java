package it.unibo.the100dayswar.model.player.api;

import java.util.Set;

import it.unibo.the100dayswar.model.unit.api.Buyable;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Unit;
import it.unibo.the100dayswar.model.unit.api.Soldier;

/**
 * Interface that defines the behavior of the possible moves that can be performed by a player.
 * This interface extends the basic Player interface by defining methods corresponding to the actions 
 * that a player can perform.
 */
public interface MutablePlayer extends Player {
    /**
     * Buys a unit, such as a soldier or tower, and deducts the cost from the player's bank account.
     *
     * @param unit the unit to buy
     * @throws IllegalStateException if the player does not have enough resources
     */
    void buyUnit(Buyable unit);
    /**
     * Removes a unit from the player's army.
     *
     * @param unit the unit to remove
     */
    void removeUnit(Unit unit);
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
     * Adds resources to the player's bank account.
     *
     * @param amount the amount of resources to add
     */
    void earnResources(int amount);
    /**
     * Returns the soldiers owned by the player.
     *
     * @return the soldiers owned by the player
     */
    Set<Soldier> getSoldiers();
}
