package it.unibo.the100dayswar.model.player.api;

import java.util.Set;

import it.unibo.the100dayswar.model.map.api.BuildableCell;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Interface that defines a player in the game.
 */
public interface Player {
    /**
     * Returns the player's name.
     *
     * @return the username of the player
     */
    String getUsername();
    /**
     * Returns the bank account of the player.
     *
     * @return the bank account of the player
     */
    BankAccount getBankAccount();
    /**
     * Returns the spawn point of player's units.
     *
     * @return the spawn point of the player
     */
    BuildableCell getSpawnPoint();
    /**
     * The units owned by the player.
     * 
     * @return the units owned by the player
     */
    Set<Unit> getUnits();
}
