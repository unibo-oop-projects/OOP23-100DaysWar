package it.unibo.the100dayswar.view.statistics;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;
import it.unibo.the100dayswar.controller.statisticscontoller.api.StatisticController;
import it.unibo.the100dayswar.model.player.api.Player;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * The view that displays the statistics of the players.
 */
public class StatisticsView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int BORDER_THICKNESS = 5;
    private static final float TITLE_FONT_SIZE = 15f;
    private static final int ROWS = 5;
    private static final int COLUMNS = 2;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;

    final StatisticController statisticController = The100DaysWar.CONTROLLER.getStatisticController();

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
       final JPanel panel = new JPanel() {
            private BufferedImage backgroundImage;
            {
                try {
                    backgroundImage = ImageIO.read(getClass().getResource("/statistic/statistics_background.png"));
                } catch (IOException e) {
                    Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, e.getMessage());
                }
            }
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    final Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    g2d.dispose();
                }
            }
        };

        panel.setLayout(new GridLayout(ROWS, COLUMNS, HORIZONTAL_GAP, VERTICAL_GAP));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK, BORDER_THICKNESS),
            player.getUsername(),
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP,
            LoadPixelFont.getFont().deriveFont(TITLE_FONT_SIZE)
        ));

        final JLabel soldiersLabel = new JLabel("Soldiers: " + statisticController.getSoldiers(player), JLabel.LEFT);
        soldiersLabel.setFont(LoadPixelFont.getFont());
        final JLabel towersLabel = new JLabel("Towers: " + statisticController.getTowers(player), JLabel.LEFT);
        towersLabel.setFont(LoadPixelFont.getFont());
        final JLabel cellsPercentageLabel = new JLabel(
            "Cells Owned(%): " + statisticController.getCellsPercentage(player), 
            JLabel.LEFT
            );
        cellsPercentageLabel.setFont(LoadPixelFont.getFont());
        final JLabel balanceLabel = new JLabel("Balance: " + statisticController.getBalance(player), JLabel.LEFT);
        balanceLabel.setFont(LoadPixelFont.getFont());

        panel.add(soldiersLabel);
        panel.add(towersLabel);
        panel.add(cellsPercentageLabel);
        panel.add(balanceLabel);
        return panel;
    }

    public void updateStatisticView() {
       statisticController.updateStatistics();
       repaint();
    }
}
