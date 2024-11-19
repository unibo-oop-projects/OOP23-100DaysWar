package it.unibo.the100dayswar.model.statistic.impl;

import java.util.Set;

import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.statistic.api.GameStatistics;
import it.unibo.the100dayswar.model.tower.api.Tower;

/**
 * The implementation of the game statistics.
 */
public class GameStatisticImpl implements GameStatistics {

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSoldiers(final Player player) {
        return (int) player.getUnits().stream()
                       .filter(unit -> unit instanceof Soldier)
                       .count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTower(final Player player) {
        return (int) player.getUnits().stream()
                       .filter(unit -> unit instanceof Tower)
                       .count();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public double getCellsPercentage(final Player player, final MapManager mapManager, final GameMap map) {
        int ownedCells = mapManager.getPlayersCells()
                               .getOrDefault(player, Set.of())
                               .size();
        long totalCells = map.getAllCells().count();
        return totalCells > 0 ? (double) ownedCells / totalCells * 100 : 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayerBalance(final Player player) {
        return player.getBankAccount().getBalance();
    }
}
