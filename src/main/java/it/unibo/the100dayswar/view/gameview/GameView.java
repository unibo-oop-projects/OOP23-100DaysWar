package it.unibo.the100dayswar.view.gameview;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

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
    private static final int DIVIDER_LOCATION = 500;
    private static final double RESIZE_WEIGHT = 0.5;
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
        final JPanel mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);

        final MapView mapView = new MapView();
        final StatisticsView statisticsView = new StatisticsView();
        final JoystickView joystickView = new JoystickView();

        final JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(statisticsView, BorderLayout.NORTH);
        rightPanel.add(joystickView, BorderLayout.SOUTH);

        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mapView, rightPanel);
        splitPane.setDividerLocation(DIVIDER_LOCATION);
        splitPane.setResizeWeight(RESIZE_WEIGHT);

        mainPanel.add(splitPane, BorderLayout.CENTER);
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
