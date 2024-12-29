package it.unibo.the100dayswar.controller.statisticContoller.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.statisticContoller.api.StatisticController;
import it.unibo.the100dayswar.model.player.api.Player;

public class StatisticControllerImpl implements StatisticController {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getSoldiers(Player player) {
        return The100DaysWar.CONTROLLER.getGameInstance()
                .getGameStatistics()
                .getSoldiers()
                .getSecond()
                .get(The100DaysWar.CONTROLLER.getGameInstance()
                    .getGameStatistics()
                    .getSoldiers()
                    .getFirst().indexOf(player));
            }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTowers(Player player) {
        return The100DaysWar.CONTROLLER.getGameInstance()
                .getGameStatistics()
                .getTowers()
                .getSecond()
                .get(The100DaysWar.CONTROLLER.getGameInstance()
                    .getGameStatistics()
                    .getTowers()
                    .getFirst().indexOf(player));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getCellsPercentage(Player player) {
        return The100DaysWar.CONTROLLER.getGameInstance()
                .getGameStatistics()
                .getCellsPercentage()
                .getSecond()
                .get(The100DaysWar.CONTROLLER.getGameInstance()
                    .getGameStatistics()
                    .getCellsPercentage()
                    .getFirst().indexOf(player));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getBalance(Player player) {
        return The100DaysWar.CONTROLLER.getGameInstance()
                .getGameStatistics()
                .getBalances()
                .getSecond()
                .get(The100DaysWar.CONTROLLER.getGameInstance()
                    .getGameStatistics()
                    .getBalances()
                    .getFirst().indexOf(player));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(The100DaysWar.CONTROLLER.getGameInstance()
        .getGameStatistics()
        .getBalances()
        .getFirst());
}

}
