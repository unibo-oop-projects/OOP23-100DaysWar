package it.unibo.the100dayswar.view.joystick;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

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
 * Class that represents the shop panel in the joystick view.
 * This panel allows the player to purchase or upgrade units.
 */
public class ShopView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final Dimension SIZE = new Dimension(200, 150);
    private static final Dimension BUTTON_SIZE = new Dimension(200, 55);
    private static final String ICON_BUTTON = "startmenu/genericbutton.jpg";

    private final JButton buySoldier;
    private final JButton buyBasicTower;
    private final JButton buyAdvancedTower;
    private final JButton upgradeUnit;

    /**
     * Constructor for the ShopView class.
     * 
     * @param mapView the map view to repaint
     */
    public ShopView(final MapView mapView) {
        super.setLayout(new GridBagLayout());
        this.buySoldier = createButton("Buy Soldier");
        this.buyBasicTower = createButton("Buy Basic Tower");
        this.buyAdvancedTower = createButton("Buy Advanced Tower");
        this.upgradeUnit = createButton("Upgrade Unit");

        setButtonActions(mapView);
        setupLayout();
        super.setPreferredSize(SIZE);
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
        final Icon icon = getIcon();
        if (icon == null) {
            throw new IllegalStateException("Icon not found");
        }
        final JButton button = new JButton(text, icon);
        final Font customFont = LoadPixelFont.getFont().deriveFont(10f);
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
     * Sets the actions for the buttons.
     * 
     * @param mapView the map view to repaint
     */
    private void setButtonActions(final MapView mapView) {
        buySoldier.addActionListener(e -> {
            The100DaysWar.CONTROLLER.getShopController().buySoldier();
            mapView.repaint();
        });
        buyBasicTower.addActionListener(e -> {
            The100DaysWar.CONTROLLER.getShopController().buyBasicTower();
            mapView.repaint();
        });
        buyAdvancedTower.addActionListener(e -> {
            The100DaysWar.CONTROLLER.getShopController().buyAdvancedTower();
            mapView.repaint();
        });
        upgradeUnit.addActionListener(e -> {
            The100DaysWar.CONTROLLER.getShopController().upgradeUnit();
            mapView.repaint();
        });
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
