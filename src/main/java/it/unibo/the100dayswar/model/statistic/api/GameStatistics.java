package it.unibo.the100dayswar.model.statistic.api;


import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.player.api.Player;

/**
 * Interface that model the game statistics.
 */
public interface GameStatistics {

    /** 
     * Get the number of soldiers of a player.
     * @param player the player.
     * @return the number of soldiers.
     */
    int getSoldiers(Player player);

    /**
     * Get the number of towers of a player.
     * @param player the player.
     * @return the number of towers.
     */
    int getTower(Player player);
    /**
     * Get the percentage of cells owned by a player.
     * @param player the player.
     * @param mapManager the map manager.
     * @param map the game map.
     * @return the percentage of cells owned by the player.
     */
    double getCellsPercentage(Player player, MapManager mapManager);

    /**
     * Get the balance of a player.
     * @param player the player.
     * @return the balance of the player.
     */
    int getPlayerBalance(Player player);
}
