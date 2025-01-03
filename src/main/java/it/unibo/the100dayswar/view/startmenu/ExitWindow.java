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

public final class ExitWindow extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final float FONT_BUTTON_NORMALIZER = (float) 1.5;

    /**
     * Creates a custom exit confirmation dialog.
     * 
     * @param parent the parent frame
     * @param backgroundImage the background image to display
     * @param labelFont the font for the text label
     * @param buttonFont the font for the buttons
     */
    private ExitWindow(final JFrame parent, final ImageIcon backgroundImage, final Font font) {
        super(parent, "Exit Confirmation", true);

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
        backgroundPanel.setPreferredSize(new Dimension(400, 200));

        final JLabel label = new JLabel("Are you sure?");
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(label, BorderLayout.CENTER);

        final float buttonFontSize = (float) (font.getSize2D() / FONT_BUTTON_NORMALIZER);
        final Font buttonFont = font.deriveFont(buttonFontSize);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Se vuoi vedere lo sfondo dietro i pulsanti
        final JButton yesButton = new JButton("Yes");
        yesButton.setFont(buttonFont);
        final JButton noButton = new JButton("No");
        noButton.setFont(buttonFont);

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

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

        this.getContentPane().add(backgroundPanel);
        this.pack();
        this.setLocationRelativeTo(parent);
    }

    /**
     * Simple utility method to show the dialog.
     */
    public static void showDialog(final JFrame parent, final String path, final Font font) {
        new ExitWindow(parent, (ImageIcon) IconLoader.loadIcon(path), font).setVisible(true);
    }
}
