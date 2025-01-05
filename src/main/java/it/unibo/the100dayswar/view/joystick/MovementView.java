package it.unibo.the100dayswar.view.joystick;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;
import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;
import it.unibo.the100dayswar.view.map.MapView;

/** 
 * Class that represents the part of the joystick that 
 * contains the buttons to move a soldier around the map.
 */
public class MovementView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final Dimension SIZE = new Dimension(200, 200);
    private static final int INSETS = 1;
    private static final Dimension BUTTON_SIZE = new Dimension(210, 70);
    private static final String ICON_BUTTON = "startmenu/genericbutton.jpg";

    private final JButton up;
    private final JButton down;
    private final JButton left;
    private final JButton right;

    /**
     * Constructor for the MovementView class.
     * 
     * @param mapView the map view to repaint
     */
    public MovementView(final MapView mapView) {
        super.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = createGridBagConstraints();

        this.up = createButton("UP");
        this.down = createButton("DOWN");
        this.left = createButton("LEFT");
        this.right = createButton("RIGHT");

        setButtonActions(mapView);
        arrangeButtons(gbc);

        super.setPreferredSize(SIZE);
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
        final Icon icon = getIcon();
        if (icon == null) {
            throw new IllegalStateException("Icon not found");
        }
        final JButton button = new JButton(text, icon);
        final Font customFont = LoadPixelFont.getFont().deriveFont(9f);
        button.setFont(customFont);
        button.setPreferredSize(BUTTON_SIZE);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false); 
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Loads the icon for the button.
     * 
     * @return the icon for the button
     */
    private Icon getIcon() {
        final Icon icon = IconLoader.loadIcon(ICON_BUTTON);
        if (icon != null) {
            final Image scaledImage = ((ImageIcon) icon).getImage()
                .getScaledInstance(BUTTON_SIZE.width, BUTTON_SIZE.height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
        return null;
    }

    /**
     * Sets actions for the joystick buttons.
     * 
     * @param mapView the map view to repaint
     */
    private void setButtonActions(final MapView mapView) {
        up.addActionListener(e -> moveUp(mapView));
        down.addActionListener(e -> moveDown(mapView));
        left.addActionListener(e -> moveLeft(mapView));
        right.addActionListener(e -> moveRight(mapView));
    }

    /**
     * Moves the soldier up.
     * 
     * @param mapView the map view to repaint.
     */
    private void moveUp(final MapView mapView) {
        The100DaysWar.CONTROLLER.getMovementController().moveUp();
        mapView.repaint();
    }

    /**
     * Moves the soldier down.
     * 
     * @param mapView the map view to repaint.
     */
    private void moveDown(final MapView mapView) {
        The100DaysWar.CONTROLLER.getMovementController().moveDown();
        mapView.repaint();
    }

    /**
     * Moves the soldier to the left.
     * 
     * @param mapView the map view to repaint.
     */
    private void moveLeft(final MapView mapView) {
        The100DaysWar.CONTROLLER.getMovementController().moveLeft();
        mapView.repaint();
    }

    /**
     * Moves the soldier to the right.
     * 
     * @param mapView the map view to repaint.
     */
    private void moveRight(final MapView mapView) {
        The100DaysWar.CONTROLLER.getMovementController().moveRight();
        mapView.repaint();
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
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSETS, INSETS, INSETS, INSETS);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        return gbc;
    }
}
