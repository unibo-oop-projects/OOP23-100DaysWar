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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;

/**
 * Utility class that implements a custom dialog to insert the player's name.
 */
public final class NameWindow {

    private static final int MAX_LENGTH = 8;
    private static final String RESOURCES = "startmenu/";
    private static final String BACKGROUND_IMAGE = RESOURCES + "calvry.jpg";

    /**
     * Private constructor to hide the implicit public one.
     */
    private NameWindow() {
    }

    /**
     * Asks the user for a username by displaying a custom dialog.
     *
     * @param parent the parent frame or null if there is no parent
     * @return the username entered by the user, or null if cancelled
     */
    public static String askUsername(final JFrame parent) {
        final NameDialog dialog = new NameDialog(parent, MAX_LENGTH, BACKGROUND_IMAGE);
        dialog.setVisible(true); // Blocks until the dialog is closed
        return dialog.getUsername();
    }

    /**
     * A custom modal dialog for entering a name with a background image.
     */
    private static final class NameDialog extends JDialog {

        private static final long serialVersionUID = 1L;

        private final int maxLength;
        private final String backgroundPath;
        private final JTextField textField = new JTextField(15);
        private String username; // Will hold the final username if valid

        /**
         * Constructs the dialog.
         *
         * @param parent         the parent frame
         * @param maxLength      the maximum allowed length for the username
         * @param backgroundPath path to the background image
         */
        NameDialog(final JFrame parent, final int maxLength, final String backgroundPath) {
            super(parent, "Username Required", true);
            this.maxLength = maxLength;
            this.backgroundPath = backgroundPath;

            initUI();
        }

        /**
         * Initializes the user interface components.
         */
        private void initUI() {
            // Load the background image
            final ImageIcon backgroundIcon = (ImageIcon) IconLoader.loadIcon(this.backgroundPath);

            // Main panel that paints the background
            final JPanel backgroundPanel = new JPanel() {
                private static final long serialVersionUID = 1L;

                @Override
                protected void paintComponent(final Graphics g) {
                    super.paintComponent(g);
                    if (backgroundIcon != null) {
                        g.drawImage(
                            backgroundIcon.getImage(),
                            0, 0,
                            getWidth(), getHeight(),
                            this
                        );
                    }
                }
            };
            backgroundPanel.setLayout(new BorderLayout());
            backgroundPanel.setPreferredSize(new Dimension(500, 600));

            // Title (or instruction) label
            final JLabel titleLabel = new JLabel("Enter your username:");
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

            // Panel for the title label
            final JPanel topPanel = new JPanel();
            topPanel.setOpaque(false); // Let the background show through
            topPanel.add(titleLabel);

            // Panel for the text field
            final JPanel centerPanel = new JPanel();
            centerPanel.setOpaque(false);
            this.textField.setFont(new Font("Arial", Font.PLAIN, 14));
            centerPanel.add(this.textField);

            // Panel for the buttons
            final JPanel buttonPanel = new JPanel();
            buttonPanel.setOpaque(false);

            final JButton okButton = new JButton("OK");
            okButton.setFont(new Font("Arial", Font.BOLD, 14));

            final JButton cancelButton = new JButton("Cancel");
            cancelButton.setFont(new Font("Arial", Font.BOLD, 14));

            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            // Add panels to the background panel
            backgroundPanel.add(topPanel, BorderLayout.NORTH);
            backgroundPanel.add(centerPanel, BorderLayout.CENTER);
            backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Add the background panel to the dialog
            this.getContentPane().add(backgroundPanel);
            this.pack();
            this.setLocationRelativeTo(getParent());

            // Action listeners for buttons
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    handleOk();
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    // User canceled; close dialog with no username
                    username = null;
                    dispose();
                }
            });
        }

        /**
         * Validates and sets the username when "OK" is pressed.
         * Closes the dialog if valid, otherwise shows an error and lets the user retry.
         */
        private void handleOk() {
            final String input = textField.getText() == null ? "" : textField.getText().trim();

            if (input.isEmpty()) {
                showErrorDialog("Username cannot be empty. Please try again.");
                return;
            } else if (input.length() > this.maxLength) {
                showErrorDialog("Username cannot exceed " + this.maxLength + " characters. Please try again.");
                return;
            }

            // If valid
            this.username = input;
            dispose();
        }

        /**
         * Shows a small error dialog (replacing or augmenting the original flow).
         *
         * @param message the error message to display
         */
        private void showErrorDialog(final String message) {
            // You could also place a custom label instead of a JOptionPane
            JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }

        /**
         * @return the valid username entered, or null if the user cancelled/closed the dialog
         */
        public String getUsername() {
            return this.username;
        }
    }
}
