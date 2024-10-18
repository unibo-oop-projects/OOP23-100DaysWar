package it.unibo.the100dayswar.model.turn;

/** 
 * An interface for the day in the game.
 */
public interface GameDay {
    /**
     * Increese the counter of the day.
     */
    void incrementDay();
    /**
     * This method returns the current day.
     * 
     * @return the current day
     * 
     */
    int getCurrentDay();
    /**
     * This mehthod increase the resources owned by the players each day.
     */
    void addIncome();
}
