package it.unibo.the100dayswar.model.statistic.api;

import it.unibo.the100dayswar.model.player.api.Player;

/**
 * Interface that model the game statistics.
 */
public interface GameStatistics {

    /** 
     * Get the number of soldiers of a player.
     * @param player the player.
     */
    int getSoldiers(Player player);

    /**
     * Get the number of towers of a player.
     * @param player the player.
     */
    int getTower(Player player);

}
