package it.unibo.the100dayswar.view.startmenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Class that models the starting menu of the game.
 */
public class StartMenuView extends JFrame {

    private static final Dimension BUTTON_SIZE = new Dimension(200, 80); // Dimensioni fisse dei pulsanti

    /**
     * Constructor of the class.
     */
    public StartMenuView() {
        super("Start Menu");

        // Basic settings of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Central panel building
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Margins between buttons

        // Layout
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

        // Finish the GUI
        add(panel, BorderLayout.CENTER);
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
        button.setMinimumSize(BUTTON_SIZE);
        button.setMaximumSize(BUTTON_SIZE);
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
        try {
            return new ImageIcon(ClassLoader.getSystemResource(path));
        } catch (NullPointerException e) {
            return new ImageIcon(); // Fallback icon
        }
    }

    /**
     * Defines the actions after pressing START.
     */
    private void startAction() {
        // TODO Initialize the game
        System.out.println("Start game!");
    }

    /**
     * Defines the actions after pressing RESUME.
     */
    private void resumeAction() {
        // TODO Resume the game
        System.out.println("Resume game!");
    }

    /**
     * Defines the actions after pressing RULES.
     */
    private void rulesAction() {
        // TODO Show game rules
        System.out.println("Show rules!");
    }

    /**
     * Defines the actions after pressing EXIT.
     */
    private void exitAction() {
        final int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Main to test.
     * 
     * @param args nothing
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(StartMenuView::new);
    }
}
