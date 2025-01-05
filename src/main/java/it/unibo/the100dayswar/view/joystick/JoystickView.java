package it.unibo.the100dayswar.view.joystick;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import it.unibo.the100dayswar.view.map.MapView;

import javax.swing.BorderFactory;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;

/**
 * Class that represents the main joystick view, combining
 * the MovementView, ShopView, and a placeholder panel into a single panel.
 */
public class JoystickView extends JPanel {

    private static final long serialVersionUID = 1L;
    private final ShopView shopView;
    private final MovementView movementView;
    private final ControlView controlView;

    /**
     * Constructor for the JoystickView class.
     * 
     * @param mapView the map view to repaint
     */
    public JoystickView(final MapView mapView) {
        super.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();

        this.movementView = new MovementView(mapView);
        this.shopView = new ShopView(mapView);
        this.controlView = new ControlView(mapView);

        this.movementView.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(
                Color.BLACK),
                "Movement",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.shopView.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(
                Color.BLACK),
                "Shop",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.controlView.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(
                Color.BLACK), 
                "Control", 
                TitledBorder.CENTER, 
                TitledBorder.TOP));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        super.add(movementView, gbc);

        gbc.gridx = 1;
        super.add(shopView, gbc);

        gbc.gridx = 2;
        super.add(controlView, gbc);
    }

    /**
     * Get the ShopView instance.
     *
     * @return the ShopView.
     */
    public ShopView getShopView() {
        return this.shopView;
    }

    /**
     * Get the MovementView instance.
     *
     * @return the MovementView.
     */
    public MovementView getMovementView() {
        return this.movementView;
    }
}
