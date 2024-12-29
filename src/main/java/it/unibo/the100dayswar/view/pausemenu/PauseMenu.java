package it.unibo.the100dayswar.view.pausemenu;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.the100dayswar.model.dice.impl.DiceImpl;
import it.unibo.the100dayswar.view.startmenu.StartMenuView;

/**
 * Class that implements the pause menu window.
 */
public class PauseMenu extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(PauseMenu.class.getName());
    // TODO private static final String SAVING_PATH = null;    // Path setted null by default.

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
        final Icon icon = loadIcon(iconPath);
        final JButton button = new JButton(text, icon);
        button.setFont(font);
        button.setPreferredSize(BUTTON_SIZE);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    }

    /**
     * Load the Icon of each button.
     * 
     * @param path the path of the image
     * 
     * @return the icon created
     */
    private Icon loadIcon(final String path) {
        return Optional.ofNullable(ClassLoader.getSystemResource(path))
            .map(ImageIcon::new)
            .orElseGet(() -> {
                LOGGER.log(Level.WARNING, "The icon at path " + path + " wasn't loaded.");
                return new ImageIcon(); // Fallback icon
            });
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
        if (saveDialog()) {
            new StartMenuView().initialize();
            dispose();
        }
    }

    /**
     * Quits the game.
     */
    private void exitGame() {
        if (saveDialog()) {
            System.exit(0);
        }
    }

    /**
     * Dialog displayed before quitting the game.
     * 
     * @return false if there is an error during the game saving 
     */
    private boolean saveDialog() {
        final int save = JOptionPane.showConfirmDialog(
            this,
            "Do you want to save the game?",
            "Exit Confirmation",
            JOptionPane.YES_NO_OPTION
        );

        if (save == JOptionPane.YES_OPTION) {
            /*
             * TODO The100DaysWar.CONTROLLER.saveGame(SAVING_PATH) quando il gioco è completo
             * 
             * this line of code must be fixed and updated when the application will work.
             */
            final int rand = new DiceImpl().roll();   // TODO needs to be killed
            if (rand % 2 == 0) {    // TODO needs to be updatev with the above line 
                JOptionPane.showMessageDialog(
                    this,
                    "Game saved successfully!",
                    "Save Status",
                    JOptionPane.INFORMATION_MESSAGE
                );
                return true;

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Failed to save the game. Please try again.",
                    "Save Status",
                    JOptionPane.ERROR_MESSAGE
                );
                return false;

            }
        }

        return true;
    }
}
