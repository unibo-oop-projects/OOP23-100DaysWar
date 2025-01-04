package it.unibo.the100dayswar.controller.gamecontroller.api;

/**
 * interface for the controller of the game.
 */
public interface GameController {
    /**
     * moethod for attack.
     */
    void attack();
    /**
     * method for pauseing the game.
     */
    void pause();
    /**
     * method for resume the game.
     */
    void resume();
    /**
     * method for quitting the game.
     */
    void quit();
}
