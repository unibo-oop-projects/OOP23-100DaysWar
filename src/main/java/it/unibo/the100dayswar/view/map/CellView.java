package it.unibo.the100dayswar.view.map;

import it.unibo.the100dayswar.commons.utilities.api.Position;

/**
 * Represents a cell view.
 */
public class CellView {
    private final Position position;
    private final String imagePath;

    /**
     * Creates a new cell view.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param imagePath the path of the image.
     */
    public CellView(Position pos, String imagePath) {
       this.position = pos;
        this.imagePath = imagePath;
    }

    /**
     * Returns the x coordinate of the cell.
     * @return the x coordinate.
      */
    public int getX() {
        return this.position.getX();
    }

    /**
     * Returns the y coordinate of the cell.
     * @return the y coordinate.
     */
    public int getY() {
        return this.position.getY();
    }

    /**
     * Returns the path of the image of the cell.
     * @return the path of the image.
     */
    public String getImagePath() {
        return imagePath;
    }
}
