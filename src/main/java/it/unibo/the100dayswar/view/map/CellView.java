package it.unibo.the100dayswar.view.map;

/**
 * Represents a cell view.
 */
public class CellView {
    private final int x;
    private final int y;
    private final String imagePath;

    /**
     * Creates a new cell view.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param imagePath the path of the image.
     */
    public CellView(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
    }

    /**
     * Returns the x coordinate of the cell.
     * @return the x coordinate.
      */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the cell.
     * @return the y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the path of the image of the cell.
     * @return the path of the image.
     */
    public String getImagePath() {
        return imagePath;
    }
}
