package it.unibo.the100dayswar.view.statistics;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.statisticscontoller.api.StatisticController;
import it.unibo.the100dayswar.model.player.api.Player;


import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The view that displays the statistics of the players.
 */
public class StatisticsView extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the statistics view.
     */
    public StatisticsView(){
        final StatisticController statisticController = The100DaysWar.CONTROLLER.getStatisticController();
        final List<Player> players = statisticController.getPlayers();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        players.forEach(player -> {
            add(createPlayerStatisticsPanel(statisticController, player));
            add(Box.createRigidArea(new Dimension(0, 10)));
        });
    }

     /**
     * Creates a panel for displaying the statistics of a single player.
     * @param statisticController the statistics controller.
     * @param player the player whose statistics are displayed.
     * @return a JPanel containing the player's statistics.
     */
    private JPanel createPlayerStatisticsPanel(final StatisticController statisticController, final Player player) {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(player.getUsername()));

        final JLabel soldiersLabel = new JLabel("Soldiers: " + statisticController.getSoldiers(player), JLabel.LEFT);
        final JLabel towersLabel = new JLabel("Towers: " + statisticController.getTowers(player), JLabel.LEFT);
        final JLabel cellsPercentageLabel = new JLabel("Cells Owned (%): " + statisticController.getCellsPercentage(player), JLabel.LEFT);
        final JLabel balanceLabel = new JLabel("Balance: " + statisticController.getBalance(player), JLabel.LEFT);

        panel.add(new JLabel("Soldiers:", JLabel.RIGHT));
        panel.add(soldiersLabel);
        panel.add(new JLabel("Towers:", JLabel.RIGHT));
        panel.add(towersLabel);
        panel.add(new JLabel("Cells Owned (%):", JLabel.RIGHT));
        panel.add(cellsPercentageLabel);
        panel.add(new JLabel("Balance:", JLabel.RIGHT));
        panel.add(balanceLabel);

        return panel;
    }

}
