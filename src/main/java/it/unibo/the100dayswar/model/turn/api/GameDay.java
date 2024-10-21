package it.unibo.the100dayswar.model.turn.api;

/** 
 * An interface for the day in the game.
 */
public interface GameDay {
    /**
     * Increase the counter of the day.
     */
    void increaseDay();
    /**
     * This method returns the current day.
     * 
     * @return the current day
     * 
     */
    int getCurrentDay();
    /**
     * Increase the resources owned by the players each day.
     */
    void addIncome();
}
