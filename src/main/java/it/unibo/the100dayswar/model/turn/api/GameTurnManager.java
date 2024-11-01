package it.unibo.the100dayswar.model.turn.api;

import it.unibo.the100dayswar.model.player.api.Player;

/**
 * An Interface for managing the turn swith between the two player.
 */
public interface GameTurnManager {
    /**
     * get the current player.
     * @return the current player
     */
    Player getCurrentPlayer();
    /**
     * get the current player.
     * @return the index of the current player in the list
     */
    int getCurrentPlayerIndex();
    /**
     * get the current turn.
     * @return the current turn
     */
    int getCurrentTurn();
    /**
     * switch Turn to the other player.
     */
    void switchTurn();
    /**
     * increase the Turn counter.
     */
    void increaseTurn();
    /**
     * check if the game end.
     * 
     * @return true if game end
     */
    boolean isGameEnd();
    /**
     * called when a player start his turn.
     */
    void playerStartTurn();
    /**
     * unsed to automatically change turn when day passes.
     */
    void onDayPassed();
    /**
     * start the timer for the day.
     */
    void dayTimer();
}
