package it.unibo.the100dayswar.model.turn.api;

/**
 * Interface for the timer.
 */
public interface Timer {
    /**
     * function for starting the timer.
     */
    void startTimer();
    /**
     * function for stopping the timer.
     */
    void stopTimer();
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
