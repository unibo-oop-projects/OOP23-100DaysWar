package it.unibo.the100dayswar.model.turn.api;

/**
 * Interface for the timer in a turn.
 */
public interface TurnTimer {
    /**
     * function for starting the timer.
     */
    void startTimer();
    /**
     * function for resetting the timer.
     */
    void resetTimer();
    /**
     * function for getting the timer.
     * 
     * @return current value of the timer
     */
    int getTimer();
}
