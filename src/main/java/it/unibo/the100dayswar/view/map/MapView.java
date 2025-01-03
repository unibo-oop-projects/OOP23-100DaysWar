package it.unibo.the100dayswar.view.map;


import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.mapcontroller.api.MapController;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.unit.api.Unit;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;


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

        addMouseListener(new MouseAdapter() {
            /**
             * Handles a mouse click event.
             * @param e the mouse event.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                Cell clickedCell = getClickedCell(e.getX(), e.getY());
                if (clickedCell != null) {
                    Optional <Unit> unit = clickedCell.getUnit();

                    // Stampa informazioni per test
                    System.out.println("Clicked on cell: " + clickedCell.getPosition());
                    unit.ifPresentOrElse(
                        u -> System.out.println("Unit present: " + u),
                        () -> System.out.println("No unit present.")
                    );
                }
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
     * Gets the cell and unit at the specified mouse coordinates.
     * @param mouseX the x-coordinate of the mouse click.
     * @param mouseY the y-coordinate of the mouse click.
     * @return a Pair containing the Optional<Unit> and the Cell, or null if the click is out of bounds.
     */
    public Cell getClickedCell(int mouseX, int mouseY) {
        int cellX = mouseX / cellSize;
        int cellY = mouseY / cellSize;

        if (cellX >= 0 && cellX < mapController.getMapWidth() && cellY >= 0 && cellY < mapController.getMapHeight()) {
            return mapController.getMap().getMap()[cellY][cellX];
        }
        return null;
    }

}
