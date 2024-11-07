package it.unibo.the100dayswar.model.bot.api;

import java.io.Serializable;

/**
 * Interface that defines the strategy used to calculate the next moves.
 */
public interface BotStrategy extends Serializable {
    /**
     * Apply the strategy to the bot player.
     * 
     * @param botPlayer the bot to apply the strategy to
     */
    void apply(BotPlayer botPlayer);
}
