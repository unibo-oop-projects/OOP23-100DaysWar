package it.unibo.the100dayswar.view.statistics;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;
import it.unibo.the100dayswar.controller.statisticscontoller.api.StatisticController;
import it.unibo.the100dayswar.model.player.api.Player;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

/**
 * The view that displays the statistics of the players.
 */
public class StatisticsView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int ROWS = 5;
    private static final int COLUMNS = 2;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;

    /**
     * Constructor of the statistics view.
     */
    public StatisticsView() {
        initializeView();
    }

    /**
     * Initializes the layout and components of the view.
     */
    private void initializeView() {
        final StatisticController statisticController = The100DaysWar.CONTROLLER.getStatisticController();
        final List<Player> players = statisticController.getPlayers();

        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
        panel.setLayout(new GridLayout(ROWS, COLUMNS, HORIZONTAL_GAP, VERTICAL_GAP));
        panel.setBorder(BorderFactory.createTitledBorder(player.getUsername()));

        final JLabel soldiersLabel = new JLabel("Soldiers: " + statisticController.getSoldiers(player), JLabel.LEFT);
        soldiersLabel.setFont(LoadPixelFont.getFont());
        final JLabel towersLabel = new JLabel("Towers: " + statisticController.getTowers(player), JLabel.LEFT);
        towersLabel.setFont(LoadPixelFont.getFont());
        final JLabel cellsPercentageLabel = new JLabel("Cells Owned(%): " 
         + statisticController.getCellsPercentage(player), JLabel.LEFT);
         cellsPercentageLabel.setFont(LoadPixelFont.getFont());
        final JLabel balanceLabel = new JLabel("Balance: " + statisticController.getBalance(player), JLabel.LEFT);
        balanceLabel.setFont(LoadPixelFont.getFont());

        panel.add(soldiersLabel);
        panel.add(towersLabel);
        panel.add(cellsPercentageLabel);
        panel.add(balanceLabel);

        return panel;
    }
}
