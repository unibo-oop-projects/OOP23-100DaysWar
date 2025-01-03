package it.unibo.the100dayswar.view.startmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;

/**
 * Utility class that implements the exitWindow after pressing the
 * Exit button in StartWindow.
 */
public final class ExitWindow extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final float FONT_BUTTON_NORMALIZER = (float) 1.5;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;

    /**
     * Simple utility method to show the dialog.
     */
    public static void showDialog(final JFrame parent, final String path, final Font font) {
        new ExitWindow(parent, (ImageIcon) IconLoader.loadIcon(path), font).setVisible(true);
    }

    /**
     * Created a custom exit window.
     * 
     * @param parent the parent JFrame.
     * @param backgroundImage the background of the window
     * @param font the font of the text in the window
     */
    private ExitWindow(final JFrame parent, final ImageIcon backgroundImage, final Font font) {
        super(parent, "Exit Confirmation", true);

        final JPanel backgroundPanel = createBackgroundPanel(backgroundImage);

        final JLabel label = createLabel(font);
        backgroundPanel.add(label, BorderLayout.CENTER);

        final JPanel buttonPanel = createButtonPanel(font);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        postInitialization(backgroundPanel, parent);
    }

    /**
     * Creates the background panel from a given backgroundImage.
     * 
     * @param backgroundImage the image in the background
     * 
     * @return the background panel
     */
    private JPanel createBackgroundPanel(final ImageIcon backgroundImage) {
        final JPanel backgroundPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(
                        backgroundImage.getImage(),
                        0, 0,
                        getWidth(), getHeight(),
                        this
                    );
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        return backgroundPanel;
    }

    /**
     * Creates the label in which display the text.
     * 
     * @param font the font of the text
     * 
     * @return the label
     */
    private JLabel createLabel(final Font font) {
        final JLabel label = new JLabel("Are you sure?");
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        return label;
    }

    /**
     * Creates a JPanel containing the two option buttons.
     * 
     * @param font the font of the texts in the button
     * 
     * @return the button panel
     */
    private JPanel createButtonPanel(final Font font) {
        final float buttonFontSize = (font.getSize2D() / FONT_BUTTON_NORMALIZER);
        final Font buttonFont = font.deriveFont(buttonFontSize);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Se vuoi vedere lo sfondo dietro i pulsanti
        final JButton yesButton = new JButton("Yes");
        yesButton.setFont(buttonFont);
        final JButton noButton = new JButton("No");
        noButton.setFont(buttonFont);

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO imposta come chiudere il gioco
                System.exit(0);
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        });

        return buttonPanel;
    }

    /**
     * Posts initialize the window.
     * 
     * @param backgroundPanel the background panel of the window
     * @param parent the parent of the window
     */
    private void postInitialization(final JPanel backgroundPanel, final JFrame parent) {
        this.getContentPane().add(backgroundPanel);
        this.pack();
        this.setLocationRelativeTo(parent);
    }
}
