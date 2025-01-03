package it.unibo.the100dayswar.view.joystick;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;

/** 
 * Class that represents the part of the joystick that 
 * contains the buttons to move a soldier around the map.
 */
public class MovementView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private static final int INSETS = 5;

    private final JButton up;
    private final JButton down;
    private final JButton left;
    private final JButton right;

    /**
     * Constructor for the MovementView class.
     */
    public MovementView() {
        super.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = createGridBagConstraints();

        this.up = createButton("UP");
        this.down = createButton("DOWN");
        this.left = createButton("LEFT");
        this.right = createButton("RIGHT");

        setButtonActions();
        arrangeButtons(gbc);

        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Disables all joystick buttons.
     */
    public void disableButtons() {
        this.up.setEnabled(false);
        this.down.setEnabled(false);
        this.left.setEnabled(false);
        this.right.setEnabled(false);
    }

    /**
     * Enables all joystick buttons.
     */
    public void enableButtons() {
        this.up.setEnabled(true);
        this.down.setEnabled(true);
        this.left.setEnabled(true);
        this.right.setEnabled(true);
    }

    /**
     * Creates a button with consistent styling.
     * 
     * @param text the text to display on the button
     * @return a styled JButton
     */
    private JButton createButton(final String text) {
        JButton button = new JButton(text);
        Font customFont = LoadPixelFont.getFont().deriveFont(10f);
        button.setFont(customFont);
        return button;
    }

    /**
     * Sets actions for the joystick buttons.
     */
    private void setButtonActions() {
        final ActionListener fakeActionListener = e -> The100DaysWar.CONTROLLER.startGame();
        up.addActionListener(fakeActionListener);
        down.addActionListener(fakeActionListener);
        left.addActionListener(fakeActionListener);
        right.addActionListener(fakeActionListener);
    }

    /**
     * Arranges the buttons in the panel.
     * 
     * @param gbc the GridBagConstraints to use.
     */
    private void arrangeButtons(final GridBagConstraints gbc) {
        gbc.gridx = 1;
        gbc.gridy = 0;
        super.add(up, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        super.add(left, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        super.add(right, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        super.add(down, gbc);
    }

    /**
     * Creates and configures the GridBagConstraints.
     * 
     * @return a configured GridBagConstraints object
     */
    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSETS, INSETS, INSETS, INSETS);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        return gbc;
    }
}
