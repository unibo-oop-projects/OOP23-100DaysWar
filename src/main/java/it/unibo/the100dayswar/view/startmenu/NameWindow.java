package it.unibo.the100dayswar.view.startmenu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Utility class that implements the Window into insert the name.
 */
public final class NameWindow {
    private static final int MAX_LENGTH = 8;

    /** 
     * A private constructor to hide the implicit public one.
     */
    private NameWindow() {
    }

    /**
     * Asks the username to the user.
     * 
     * @param parent the parent frame
     * 
     * @return the username
     */
    public static String askUsername(final JFrame parent) {
        final String username = JOptionPane.showInputDialog(
            parent,
            "Enter your username:",
            "Username Required",
            JOptionPane.QUESTION_MESSAGE
        );

        if (username == null || username.isBlank()) {
            JOptionPane.showMessageDialog(
                parent,
                "Username cannot be empty. Please try again.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            return askUsername(parent);
        } else if (username.trim().length() >= MAX_LENGTH) {
            JOptionPane.showMessageDialog(
                parent,
                "Username cannot exceed " + MAX_LENGTH + " characters. Please try again.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            return askUsername(parent);
        }

        return username.trim();
    }
}
