package it.unibo.the100dayswar.view.startmenu;

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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.view.rules.RulesViewer;

/**
 * Class that models the starting menu of the game.
 */
public class StartMenuView extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(StartMenuView.class.getName());
    private static final String PATH = null;    // The path is 'null' by default. 

    private static final int WIDTH = 200;
    private static final int HEIGHT = 80;
    private static final int MARGINS = 20;
    private static final Dimension BUTTON_SIZE = new Dimension(WIDTH, HEIGHT);

    /**
     * Constructor of the class.
     * 
     */
    public StartMenuView() {
        super("Start Menu");
    }

    /**
     * Initialize the class.
     * 
     * @implNote this method must be final to avoid ConstructorCallsOverridableMethod.
     */
    public final void initialize() {
        buildUI(); 
        postInitialization();
    }

    /**
     * Builds the UI components.
     */
    private void buildUI() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(MARGINS, MARGINS, MARGINS, MARGINS);

        final Font buttonFont = new Font("Arial", Font.BOLD, 24);
        final String resources = "startmenu/";

        /*
         * START
         */
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JButton btnStart = createButton("START", resources + "start.png", buttonFont);
        btnStart.addActionListener(st -> startAction());
        panel.add(btnStart, gbc);

        /*
         * RESUME
         */
        gbc.gridy++;
        final JButton btnResume = createButton("RESUME", resources + "resume.png", buttonFont);
        btnResume.addActionListener(re -> resumeAction());
        panel.add(btnResume, gbc);

        /*
         * RULES
         */
        gbc.gridy++;
        final JButton btnRules = createButton("RULES", resources + "rules.png", buttonFont);
        btnRules.addActionListener(ru -> rulesAction());
        panel.add(btnRules, gbc);

        /*
         * EXIT
         */
        gbc.gridy++;
        final JButton btnExit = createButton("EXIT", resources + "exit.png", buttonFont);
        btnExit.addActionListener(ex -> exitAction());
        panel.add(btnExit, gbc);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Final initialization step for frame configuration.
     */
    private void postInitialization() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Creates a button with the given text, icon, and font.
     * 
     * @param text the text of the button
     * @param iconPath the path to the button's icon
     * @param font the font of the button
     * @return the created button
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
     * Loads an icon from the specified path.
     * 
     * @param path the path to the icon
     * @return the loaded icon or a fallback if not found
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
     * Defines the actions after pressing START.
     */
    private void startAction() {
        The100DaysWar.CONTROLLER.getGameInstance().addPlayer(NameWindow.askUsername(this));
        // TODO Implement start logic
        // TODO new GameTable() ....
        dispose();
    }

    /**
     * Defines the actions after pressing RESUME.
     */
    private void resumeAction() {
        // TODO Implement resume logic
        if (The100DaysWar.CONTROLLER.loadOldGame(PATH)) {
            // TODO launch game window
            dispose();
        } else {
            NoOldGameFoundWindow.show(this);;
        }
    }

    /**
     * Defines the actions after pressing RULES.
     */
    private void rulesAction() {
       new RulesViewer().setString();
    }

    /**
     * Defines the actions after pressing EXIT.
     */
    private void exitAction() {
        ExitWindow.show(this);
    }

    /**
     * Main to test.
     * TODO KILL THIS MAIN
     * @param args nothing
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final StartMenuView view = new StartMenuView();
            view.initialize();
        });
    }
}
