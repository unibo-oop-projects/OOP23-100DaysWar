package it.unibo.the100dayswar.model.bot.api;

/**
 * Interface that rapresents a bot player in the game.
 */
public interface BotPlayer {
    /** 
     * The method that will update the bot's state and make the move 
     * according to the strategy.
     */
    void makeMove();
    /**
     * Sets the strategy of the bot player.
     * 
     * @param strategy the strategy to set
     */
    void setStrategy(BotStrategy strategy);
}
