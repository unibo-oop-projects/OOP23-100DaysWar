package it.unibo.the100dayswar.controller.movementcontroller.api;

/**
 * The interface of the movement controller that provides
 * the methods to control the movement of the soldiers.
 */
public interface MovementController {
    
    /** 
     * Move the selecetd soldier up.
     */
    public void moveUp();

    /** 
     * Move the selecetd soldier down.
     */
    public void moveDown();

    /** 
     * Move the selecetd soldier left.
     */
    public void moveLeft();

    /** 
     * Move the selecetd soldier right.
     */
    public void moveRight();
}
