package it.unibo.the100dayswar.view.startmenu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Utility class that implements the Window to exit the game.
 */
public final class  ExitWindow {

    /**
     * A private constructor to hide the implicit public one.
     */
    private ExitWindow() {
    }

    /**
     * Display a confirmation dialog to be sure that the user really
     * wants to exit the game.
     * 
     * @param parent the parent frame or null if no parent is available
     */
    public static void show(final JFrame parent) {
        final int confirm = JOptionPane.showConfirmDialog(
            parent,
            "Are you sure you want to exit?",
            "Exit Confirmation",
            JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
