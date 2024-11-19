package it.unibo.the100dayswar.model.statistic.impl;

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
    public int getCellsPercentage(final Player player, final MapManager mapManager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCellsPercentage'");
    }

}
