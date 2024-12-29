package it.unibo.the100dayswar.view.joystick;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.the100dayswar.application.The100DaysWar;

/** 
 * Class that represents the part of the joystick that 
 * contains the buttons to move a soldier around the map.
 */
public class MovementView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private final JButton up;
    private final JButton down;
    private final JButton left;
    private final JButton right;

    /**
     * Constructor for the MovementView class.
     */
    public MovementView() {
        super.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);
    
        this.up = new JButton("UP");
        this.down = new JButton("DOWN");
        this.left = new JButton("LEFT");
        this.right = new JButton("RIGHT");

        final ActionListener fakeActionListener = e -> The100DaysWar.CONTROLLER.startGame();
        
        up.addActionListener(fakeActionListener);
        down.addActionListener(fakeActionListener);
        left.addActionListener(fakeActionListener);
        right.addActionListener(fakeActionListener);

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
    
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Disable all joystick buttons.
     */
    public void disableButtons() {
        this.up.setEnabled(false);
        this.down.setEnabled(false);
        this.left.setEnabled(false);
        this.right.setEnabled(false);
    }

    /**
     * Enable all joystick buttons.
     */
    public void enableButtons() {
        this.up.setEnabled(true);
        this.down.setEnabled(true);
        this.left.setEnabled(true);
        this.right.setEnabled(true);
    }
}
