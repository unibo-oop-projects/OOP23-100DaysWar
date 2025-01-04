package it.unibo.the100dayswar.view.gameview;

import javax.swing.*;
import java.awt.*;
import it.unibo.the100dayswar.view.joystick.JoystickView;
import it.unibo.the100dayswar.view.map.MapView;
import it.unibo.the100dayswar.view.statistics.StatisticsView;

/**
 * The main frame of the game composed by 
 * the map in the left half and the statistics of the game
 * and the joystick for the player in the right half.
 */
public class GameView extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 900;

    /**
     * Constructor for the GameView class.
     */
    public GameView() {
        super("Game View");
    }

    /**
     * Initializes the frame.
     */
    public final void initialize() {
        setUI();
        setPostInitialize();
    }

    /**
     * Configures the main UI layout and components.
     */
    private void setUI() {
        final JPanel mainPanel = new JPanel(new GridBagLayout());
        this.setContentPane(mainPanel);

        final MapView mapView = new MapView();
        final StatisticsView statisticsView = new StatisticsView();
        final JoystickView joystickView = new JoystickView();

        final GridBagConstraints gbc = new GridBagConstraints();

        // MapView (Sinistra - Quadrata)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(mapView, gbc);

        // StatisticsView (Destra - Sopra)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        mainPanel.add(statisticsView, gbc);

        // JoystickView (Destra - Sotto)
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        mainPanel.add(joystickView, gbc);
    }

    /**
     * Final initialization step for frame configuration.
     */
    private void setPostInitialize() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
