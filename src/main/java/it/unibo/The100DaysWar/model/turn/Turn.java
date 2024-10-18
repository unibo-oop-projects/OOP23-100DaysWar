package it.unibo.the100dayswar.model.turn;

/**
 * An interface rappresenting the turn of a player.
 */
public interface Turn {
    /**
    * A function executing an action made by player.
    */
    void executeAction();
    /**
    * A function changing the player turn.
    */
    void changeTurn();
}
