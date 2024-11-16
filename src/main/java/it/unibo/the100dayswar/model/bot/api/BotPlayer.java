package it.unibo.the100dayswar.model.bot.api;

import it.unibo.the100dayswar.model.player.api.Player;

/**
 * Interface that rapresents a bot player in the game.
 */
public interface BotPlayer extends Player {
    /** 
     * The method that will update the bot's state and make the move 
     * according to the current strategy.
     */
    void makeMove();
}
