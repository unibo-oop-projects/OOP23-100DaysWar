package it.unibo.the100dayswar.model.map.api;
/**
 * Interface a model a  cell.
 */
public interface Cell {
    /**
     * getter x postion.
     * @return the orizontal position in te map.
    */
    int getX();

    /**
    * getter y postion.
    * @return the vrtical position in te map.
    */
    int getY(); 

    /**
     * @return true if the cell il occupied by a soldier or a tower.
     */
    boolean isOccupied();

    /**
     * setter of the attribute isOccupied.
     * @param occupation is true if the cell is occupated.
     */
    void setOccupation(boolean occupation);
}
