package it.unibo.the100dayswar.view.gameover;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;

/**
 * Abstract class that represents the game over view.
 */
public abstract class AbstractGameOverView extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the AbstractGameOverView class.
     * @param messageText the message to display
     * @param backgroundImgPath the path to the background image
     */
    protected void closeAllWindows() {
        Arrays.stream(getWindows())
            .filter(Window::isVisible)
            .forEach(Window::dispose);
    }

    /**
     * Loads an image from the given path.
     * @param path the path to the image
     * @return the loaded image
     */
    protected Image loadImage(final String path) {
        try {
            final URL imageUrl = AbstractGameOverView.class.getResource(path);
            return ImageIO.read(imageUrl);
        } catch (IOException e) {
            throw new IllegalArgumentException("Image not found: " + path);
        }
    }

    /**
     * Sets up the UI of the game over view.
     * @param messageText the message to display
     * @param backgroundImgPath the path to the background image
     */
    protected void setupUI(final String messageText, final String backgroundImgPath) {
        final Image backgroundImg = loadImage(backgroundImgPath);
        final JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (backgroundImg != null) {
                    g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        contentPanel.setLayout(new BorderLayout());
        setContentPane(contentPanel);

        final JPanel overlayPanel = new JPanel(new BorderLayout());
        overlayPanel.setOpaque(false);
        overlayPanel.setBackground(new Color(0, 0, 0, 100));
        
        final JLabel message = new JLabel(messageText, JLabel.CENTER);
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
}
