package it.unibo.the100dayswar.model.turn.api;

import it.unibo.the100dayswar.model.player.api.Player;

/**
 * An Interface for managing the turn swith between the two player.
 */
public interface GameTurn {
    /**
     * get the current player.
     * @return the current player
     */
    Player getCurrentPlayer();
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
}
