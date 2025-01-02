package it.unibo.the100dayswar.view.pausemenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;
import it.unibo.the100dayswar.view.startmenu.StartMenuView;

/**
 * Class that implements the pause menu window.
 */
public class PauseMenu extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final String SAVING_PATH = null;    // The SAVING_PATH is 'null' by default.

    private static final int MARGINS = 20;
    private static final int WIDTH = 250;
    private static final int HEIGHT = 100;
    private static final Dimension BUTTON_SIZE = new Dimension(WIDTH, HEIGHT);

    private static final int POST_INIT_WIDTH = 400;
    private static final int POST_INIT_HEIGHT = 300;

    /**
     * Constructor of the pausing window.
     * 
     * @param parent the panel frame of the pausing window
     */
    public PauseMenu(final JFrame parent) {
        super(parent, "Paused", true);
    }

    /**
     * Initialize the window.
     */
    public void initialize() {
        buildUI();
        postInitialization();
    }

    /**
     * Build the UI.
     */
    private void buildUI() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(MARGINS, MARGINS, MARGINS, MARGINS);

        final Font buttonFont = new Font("Arial", Font.BOLD, 24);
        final String resources = "pausemenu/";

        /*
         * RESUME
         */
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JButton btnResume = createButton("RESUME", resources + "resume.png", buttonFont);
        btnResume.addActionListener(e -> resumeGame());
        panel.add(btnResume, gbc);

        /*
         * MAIN MENU
         */
        gbc.gridy++;
        final JButton btnMainMenu = createButton("MAIN MENU", resources + "mainmenu.png", buttonFont);
        btnMainMenu.addActionListener(e -> returnToMainMenu());
        panel.add(btnMainMenu, gbc);

        /*
         * EXIT
         */
        gbc.gridy++;
        final JButton btnExit = createButton("EXIT", resources + "exit.png", buttonFont);
        btnExit.addActionListener(e -> exitGame());
        panel.add(btnExit, gbc);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Posts initialize the window.
     */
    private void postInitialization() {
        setSize(POST_INIT_WIDTH, POST_INIT_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Create a generic button from the given parameters.
     * 
     * @param text the text of the button
     * @param iconPath the path of the icon
     * @param font the font of the text
     * 
     * @return the button created
     */
    private JButton createButton(final String text, final String iconPath, final Font font) {
        final Icon icon = IconLoader.loadIcon(iconPath);
        final JButton button = new JButton(text, icon);
        button.setFont(font);
        button.setPreferredSize(BUTTON_SIZE);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    }

    /**
     * Reasume the game.
     */
    private void resumeGame() {
        dispose();
    }

    /**
     * Returns to the main menu.
     */
    private void returnToMainMenu() {
        if (SaveWindow.saveDialog(this, SAVING_PATH)) {
            new StartMenuView().initialize();
            dispose();
        }
    }

    /**
     * Quits the game.
     */
    private void exitGame() {
        if (ExitWindowFromPauseMenu.exitDialog(this) 
                && SaveWindow.saveDialog(this, SAVING_PATH)) { 
            System.exit(0);
        }
    }
}
