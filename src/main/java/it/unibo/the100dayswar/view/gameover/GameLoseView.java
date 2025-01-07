package it.unibo.the100dayswar.view.gameover;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Logger;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;

/**
 * View displayed when the player loses the game.
 */
public class GameLoseView extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    private static final int OPACITY = 150;

    /**
     * Constructor for the GameLoseView.
     */
    public GameLoseView() {
        super("Defeat...");
    }

    /**
     * Initializes the frame, setting up the UI and final configuration.
     */
    public final void initialize() {
        setUI();
        setPostInitialize();
    }

    /**
     * Configures the frame settings after the UI is set up.
     */
    private void setPostInitialize() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Sets up the UI components without using a custom BackgroundPanel.
     */
    private void setUI() {
        final Image backgroundImg = loadImage("/gameover/lose.png");

        final JPanel contentPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (backgroundImg != null) {
                    g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        contentPanel.setLayout(new BorderLayout());
        this.setContentPane(contentPanel);

        final JPanel overlayPanel = new JPanel(new BorderLayout());
        overlayPanel.setOpaque(false);
        overlayPanel.setBackground(new Color(0, 0, 0, OPACITY));

        final JLabel message = new JLabel("Sorry, you lost!", JLabel.CENTER);
        final Font customFont = LoadPixelFont.getFont().deriveFont(24f);
        message.setFont(customFont);
        message.setForeground(Color.WHITE);
        overlayPanel.add(message, BorderLayout.CENTER);

        final JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setFont(customFont);
        mainMenuButton.addActionListener(e -> {
            closeAllWindows();
            The100DaysWar.CONTROLLER.startGame();
            dispose();
        });
        overlayPanel.add(mainMenuButton, BorderLayout.SOUTH);

        contentPanel.add(overlayPanel, BorderLayout.CENTER);
    }

    /**
     * Closes all open windows in the application.
     */
    private void closeAllWindows() {
        Arrays.stream(getWindows())
            .filter(Window::isVisible)
            .forEach(Window::dispose);
    }

    /**
     * Loads an image from the given path.
     *
     * @param path the path of the image to load
     * @return the loaded image, or null if not found
     */
    private Image loadImage(final String path) {
        try {
            final URL imageUrl = GameLoseView.class.getResource(path);
            if (imageUrl == null) {
                throw new IllegalArgumentException("Image not found at path: " + path);
            }
            return ImageIO.read(imageUrl);
        } catch (IOException e) {
            Logger.getGlobal().severe("Error loading image: " + e.getMessage());
            return null;
        }
    }
}
