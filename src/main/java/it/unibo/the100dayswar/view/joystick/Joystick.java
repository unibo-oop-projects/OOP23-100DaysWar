package it.unibo.the100dayswar.view.joystick;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class that represents the joystick Panel in the view.
 * This panel is divided in two parts: one for the shop and one
 * with the actions like pause, resume and quit.
 */
public class Joystick extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private final JButton buySoldier;
    private final JButton buyBasicTower;
    private final JButton buyAdvancedTower;
    private final JButton upgradeUnit;

    /** 
     * 
     */
    public Joystick() {
        super.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        
        this.buySoldier = new JButton("BUY SOLDIER");
        this.buyBasicTower = new JButton("BUY BASIC TOWER");
        this.buyAdvancedTower = new JButton("BUY ADVANCED TOWER");
        this.upgradeUnit = new JButton("UPGRADE UNIT");

        //TODO add action listener to buttons

        //TODO place buttons with grid layout

        gbc.gridx = 1;
        gbc.gridy = 0;
        super.add(this.buySoldier);

        gbc.gridx = 0;
        gbc.gridy = 1;
        super.add(this.buyBasicTower);

        gbc.gridx = 2;
        gbc.gridy = 1;
        super.add(this.buyAdvancedTower);

        gbc.gridx = 1;
        gbc.gridy = 2;
        super.add(this.upgradeUnit);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
