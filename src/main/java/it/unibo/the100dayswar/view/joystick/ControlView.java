package it.unibo.the100dayswar.view.joystick;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;
import it.unibo.the100dayswar.view.pausemenu.PauseMenu;
import it.unibo.the100dayswar.view.rules.RulesViewer;
import it.unibo.the100dayswar.view.startmenu.ExitWindow;

/**
 * Class that represents the shop panel in the joystick view.
 * This panel allows the player to purchase or upgrade units.
 */
public class ControlView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 150;

    private final JButton attack;
    private final JButton pause;
    private final JButton resume;
    private final JButton rules;
    private final JButton quit;

    /**
     * Constructor for the ShopView class.
     */
    public ControlView() {
        super.setLayout(new GridBagLayout());
        this.attack = createButton("Attack");
        this.pause = createButton("Pause");
        this.resume = createButton("Resume");
        this.rules = createButton("Read Rules");
        this.quit = createButton("Quit");

        setButtonActions();
        setupLayout();
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Disables all shop buttons.
     */
    public void disableButtons() {
        this.attack.setEnabled(false);
        this.pause.setEnabled(false);
        this.resume.setEnabled(false);
        this.rules.setEnabled(false);
        this.quit.setEnabled(false);
    }

    /**
     * Enables all shop buttons.
     */
    public void enableButtons() {
        this.attack.setEnabled(true);
        this.pause.setEnabled(true);
        this.resume.setEnabled(true);
        this.rules.setEnabled(true);
        this.quit.setEnabled(true);
    }

    /**
     * Creates a button with consistent styling.
     *
     * @param text the text to display on the button
     * @return a styled JButton
     */
    private JButton createButton(final String text) {
        final JButton button = new JButton(text);
        final Font customFont = LoadPixelFont.getFont().deriveFont(10f);
        button.setFont(customFont);
        return button;
    }

    /**
     * Sets the actions for the buttons.
     */
    private void setButtonActions() {
        attack.addActionListener(e -> attackAction());
        pause.addActionListener(e -> pauseAction());
        resume.addActionListener(e -> resumeAction());
        rules.addActionListener(e -> rulesAction());
        quit.addActionListener(e -> exitAction());
    }

    /**
     * Arranges the buttons in the layout.
     */
    private void setupLayout() {
        final GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        super.add(attack, gbc);

        gbc.gridy = 1;
        super.add(pause, gbc);

        gbc.gridy = 2;
        super.add(resume, gbc);

        gbc.gridy = 3;
        super.add(rules, gbc);
        gbc.gridy = 4;
        super.add(quit, gbc);
    }

    /**
     * The action to be performed when the attack button is clicked.
     */
    private void attackAction() {
    }

    /**
     * Defines the actions after pressing RESUME.
     */
    private void resumeAction() {
    }

    /** 
     * The action to be performed when the pause button is clicked.
     */
    private void pauseAction() {
        new PauseMenu(null).initialize();
    }

    /**
     * The action to be performed when the rules button is clicked.
     */
    private void rulesAction() {
       new RulesViewer().intitialize();
    }

    /**
     * The action to be performed when the quit button is clicked.
     */
    private void exitAction() {
        ExitWindow.showDialog(null);
    }
}
