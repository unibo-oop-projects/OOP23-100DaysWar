package it.unibo.the100dayswar.view.map;


import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.mapcontroller.api.MapController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import java.util.List;


/**
 * View for rendering the game map with a background image and a grid overlay.
 */
public class MapView extends JPanel {

    private static final int CELL_SIZE = 50;
    private static final String MAP_IMAGE_PATH = "/map/map.png";
    private static final long serialVersionUID = 1L;
    private final MapController mapController;
    private final Image mapImage;
    private final int cellSize;

    /**
     * Constructor for MapView.
     * @param mapController the map controller.
     * @param mapImagePath the path to the map background image.
     */
    public MapView() {
        this.mapController = The100DaysWar.CONTROLLER.getMapController();
        this.cellSize = CELL_SIZE;
        this.mapImage = loadImage(MAP_IMAGE_PATH);
        setLayout(null);
    }

    /**
     * Loads an image from the given path.
     * @param path the image path.
     * @return the loaded Image.
     */
    private Image loadImage(final String path) {
        try {
            final URL imageUrl = getClass().getResource(path);
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
        final MapController mapController = The100DaysWar.CONTROLLER.getMapController();
        final int totalWidth = mapController.getMapWidth() * CELL_SIZE;
        final int totalHeight = mapController.getMapHeight() * CELL_SIZE;
        g.drawImage(mapImage, 0, 0, totalWidth, totalHeight, this);

        final List<CellView> cellsView = mapController.getCellsView();

        for (CellView cellView : cellsView) {
            final int xPos = cellView.getX() * CELL_SIZE;
            final int yPos = cellView.getY() * CELL_SIZE;

            final Image cellImage = loadImage(cellView.getImagePath());
            if (cellImage != null) {
                g.drawImage(cellImage, xPos, yPos, CELL_SIZE, CELL_SIZE, this);
            }

            g.setColor(Color.BLACK);
            g.drawRect(xPos, yPos, CELL_SIZE, CELL_SIZE);
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
}
