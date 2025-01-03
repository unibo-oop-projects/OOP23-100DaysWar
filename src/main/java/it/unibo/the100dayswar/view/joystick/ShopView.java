package it.unibo.the100dayswar.view.joystick;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;

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
        this.buySoldier = createButton("Buy Soldier");
        this.buyBasicTower = createButton("Buy Basic Tower");
        this.buyAdvancedTower = createButton("Buy Advanced Tower");
        this.upgradeUnit = createButton("Upgrade Unit");

        setButtonActions();
        setupLayout();
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Disables all shop buttons.
     */
    public void disableButtons() {
        this.buyBasicTower.setEnabled(false);
        this.buySoldier.setEnabled(false);
        this.buyAdvancedTower.setEnabled(false);
        this.upgradeUnit.setEnabled(false);
    }

    /**
     * Enables all shop buttons.
     */
    public void enableButtons() {
        this.buyBasicTower.setEnabled(true);
        this.buySoldier.setEnabled(true);
        this.buyAdvancedTower.setEnabled(true);
        this.upgradeUnit.setEnabled(true);
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
     * Sets the actions for the buttons.
     */
    private void setButtonActions() {
        buyBasicTower.addActionListener(e -> The100DaysWar.CONTROLLER.getShopController().buyBasicTower());
        buySoldier.addActionListener(e -> The100DaysWar.CONTROLLER.getShopController().buySoldier());
        buyAdvancedTower.addActionListener(e -> The100DaysWar.CONTROLLER.getShopController().buyAdvancedTower());
        upgradeUnit.addActionListener(e -> The100DaysWar.CONTROLLER.getShopController().upgradeUnit(null));
        //  todo: Implementare la selezione di una cella/un'unità
    }

    /**
     * Arranges the buttons in the layout.
     */
    private void setupLayout() {
        final GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        super.add(buySoldier, gbc);

        gbc.gridy = 1;
        super.add(buyBasicTower, gbc);

        gbc.gridy = 2;
        super.add(buyAdvancedTower, gbc);

        gbc.gridy = 3;
        super.add(upgradeUnit, gbc);
    }
}
