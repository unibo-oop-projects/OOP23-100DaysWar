package it.unibo.the100dayswar.view.joystick;

import javax.swing.JPanel;

import it.unibo.the100dayswar.application.The100DaysWar;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;

/**
 * Class that represents the shop panel in the joystick view.
 * This panel allows the player to purchase or upgrade units.
 */
public class ShopView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 150;
    private final JButton buySoldier;
    private final JButton buyBasicTower;
    private final JButton buyAdvancedTower;
    private final JButton upgradeUnit;

    /**
     * Constructor for the ShopView class.
     */
    public ShopView() {
        super.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();

        this.buySoldier = new JButton("Buy Soldier");
        this.buyBasicTower = new JButton("Buy Basic Tower");
        this.buyAdvancedTower = new JButton("Buy Advanced Tower");
        this.upgradeUnit = new JButton("Upgrade Unit");

        final ActionListener fakeActionListener = e -> The100DaysWar.CONTROLLER.startGame();

        buyBasicTower.addActionListener(fakeActionListener);
        buySoldier.addActionListener(fakeActionListener);
        buyAdvancedTower.addActionListener(fakeActionListener);
        upgradeUnit.addActionListener(fakeActionListener);

        gbc.gridx = 0;
        gbc.gridy = 0;
        super.add(buySoldier, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        super.add(buyBasicTower, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        super.add(buyAdvancedTower, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        super.add(upgradeUnit, gbc);

        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Disable all shop buttons.
     */
    public void disableButtons() {
        this.buyBasicTower.setEnabled(false);
        this.buySoldier.setEnabled(false);
        this.buyAdvancedTower.setEnabled(false);
        this.upgradeUnit.setEnabled(false);
    }

    /**
     * Enable all shop buttons.
     */
    public void enableButtons() {
        this.buyBasicTower.setEnabled(true);
        this.buySoldier.setEnabled(true);
        this.buyAdvancedTower.setEnabled(true);
        this.upgradeUnit.setEnabled(true);
    }
}