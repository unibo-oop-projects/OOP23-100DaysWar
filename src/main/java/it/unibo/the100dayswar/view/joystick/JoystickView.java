package it.unibo.the100dayswar.view.joystick;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;
import it.unibo.the100dayswar.view.map.MapView;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private Image backgroundImage;

    /**
     * Constructor for the JoystickView class.
     * 
     * @param mapView the map view to repaint
     */
    public JoystickView(final MapView mapView) {
        loadBackgroundImage();
        super.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();

        this.movementView = new MovementView(mapView);
        this.shopView = new ShopView(mapView);
        this.controlView = new ControlView(mapView);

        customizePanel(this.movementView, "Movement");
        customizePanel(this.shopView, "Shop");
        customizePanel(this.controlView, "Control");

        /* 
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
*/
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
     * Loads the background image.
     */
    private void loadBackgroundImage() {
        try (InputStream is = getClass().getResourceAsStream("/joystick/joystick_background.png")) {
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found at " + "/joystick/joystick_background.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Customizes the given panel with a title, thicker border, and a custom font.
     *
     * @param panel the panel to customize
     * @param title the title for the panel
     */
    private void customizePanel(JPanel panel, String title) {
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK, 5),
            title,
            TitledBorder.CENTER,
            TitledBorder.TOP,
            LoadPixelFont.getFont().deriveFont(15f),
            Color.BLACK
        ));
        panel.setOpaque(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    if (backgroundImage != null) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();
    }
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
