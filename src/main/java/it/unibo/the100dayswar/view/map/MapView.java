package it.unibo.the100dayswar.view.map;

import it.unibo.the100dayswar.controller.mapcontroller.api.MapController;
import it.unibo.the100dayswar.model.cell.api.Cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;


/**
 * View for rendering the game map with a background image and a grid overlay.
 */
public class MapView extends JPanel {

    private static final int CELL_SIZE = 50;
    private static final long serialVersionUID = 1L;
    private final MapController mapController;
    private final Image mapImage;
    private final int cellSize;

    /**
     * Constructor for MapView.
     * @param mapController the map controller.
     * @param mapImagePath the path to the map background image.
     */
    public MapView(final MapController mapController, final String mapImagePath) {
        this.mapController = mapController;
        this.cellSize = CELL_SIZE;
        this.mapImage = loadImage(mapImagePath);
        setLayout(null);

        addMouseListener(new MouseAdapter() {
            /**
             * Handles a mouse click event.
             * @param e the mouse event.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                handleCellClick(e.getX(), e.getY());
            }
        });
    }

    /**
     * Loads an image from the given path.
     * @param path the image path.
     * @return the loaded Image.
     */
    private Image loadImage(final String path) {
        try {
            final URL imageUrl = getClass().getClassLoader().getResource(path);
            if (imageUrl != null) {
                return ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image not found: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Paints the map view.
     * @param g the graphics object.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final int totalWidth = mapController.getMapWidth() * cellSize;
        final int totalHeight = mapController.getMapHeight() * cellSize;
        g.drawImage(mapImage, 0, 0, totalWidth, totalHeight, this);

        final Image obstacleImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/map/obstacle.png"));

        for (int y = 0; y < mapController.getMapHeight(); y++) {
            for (int x = 0; x < mapController.getMapWidth(); x++) {
                Cell cell = mapController.getMap().getMap()[y][x];
                final int xPos = x * cellSize;
                final int yPos = y * cellSize;

                if (!cell.isBuildable()) {
                    g.drawImage(obstacleImage, xPos, yPos, cellSize, cellSize, this);
                }

                g.setColor(Color.BLACK);
                g.drawRect(xPos, yPos, cellSize, cellSize);
            }
        }
    }

    /**
     * Returns the preferred size of the map view.
     * @return the preferred size.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(mapController.getMapWidth() * cellSize, mapController.getMapHeight() * cellSize);
    }

    /**
     * Handles a cell click event.
     * @param mouseX the x-coordinate of the mouse click.
     * @param mouseY the y-coordinate of the mouse click.
     */
    private void handleCellClick(final int mouseX, final int mouseY) {
        final int cellX = mouseX / cellSize;
        final int cellY = mouseY / cellSize;

        if (cellX >= 0 && cellX < mapController.getMapWidth() && cellY >= 0 && cellY < mapController.getMapHeight()) {
            final Cell clickedCell = mapController.getMap().getMap()[cellY][cellX];
            if (clickedCell.isBuildable() && clickedCell.isFree()) {
                openShopView(clickedCell, mouseX, mouseY);
            }
        }
    }

    /**
     * Opens the ShopView for the selected cell.
     * @param cell the cell to perform actions on.
     * @param mouseX the x-coordinate of the mouse click.
     * @param mouseY the y-coordinate of the mouse click.
     */
    private void openShopView(Cell cell, int mouseX, int mouseY) {
        JFrame shopFrame = new JFrame("Shop");
        shopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shopFrame.setSize(300, 200);
        shopFrame.setLocation(mouseX + 10, mouseY + 10);
        shopFrame.add(new JLabel("Shop for cell: " + cell.getPosition()));
        shopFrame.setVisible(true);
    }
}
