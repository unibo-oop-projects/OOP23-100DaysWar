package it.unibo.the100dayswar.view.startmenu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class  NoOldGameFoundWindow {
    
    private NoOldGameFoundWindow() {
    }

    /**
     * Displays an error dialog with the message "No old game was found".
     * 
     * @param parent the parent frame or null if no parent is available
     */
    public static void show(final JFrame parent) {
        JOptionPane.showMessageDialog(
            parent,
            "No old game was found",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
