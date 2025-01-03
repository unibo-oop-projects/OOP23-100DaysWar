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

public final class ExitWindow extends JDialog {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a custom exit confirmation dialog.
     * 
     * @param parent the parent frame
     * @param backgroundImage the background image to display
     * @param labelFont the font for the text label
     * @param buttonFont the font for the buttons
     */
    public ExitWindow(final JFrame parent,
                            final ImageIcon backgroundImage,
                            final Font labelFont,
                            final Font buttonFont) {
        super(parent, "Exit Confirmation", true); // modal dialog

        // Pannello per disegnare l'immagine di sfondo
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

        // Etichetta di testo
        final JLabel label = new JLabel("Are you sure?");
        label.setFont(labelFont);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(label, BorderLayout.CENTER);

        // Pannello per i pulsanti
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Se vuoi vedere lo sfondo dietro i pulsanti
        final JButton yesButton = new JButton("Yes");
        yesButton.setFont(buttonFont);
        final JButton noButton = new JButton("No");
        noButton.setFont(buttonFont);

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Eventi pulsanti
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.exit(0); // o altro tipo di logica di chiusura
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose(); // chiudi il dialog
            }
        });

        this.getContentPane().add(backgroundPanel);
        this.pack();
        this.setLocationRelativeTo(parent);
    }

    /**
     * Simple utility method to show the dialog.
     */
    public static void showDialog(final JFrame parent,
                                  final ImageIcon backgroundImage,
                                  final Font labelFont,
                                  final Font buttonFont) {
        final ExitWindow dialog = new ExitWindow(parent, backgroundImage, labelFont, buttonFont);
        dialog.setVisible(true);
    }
}
