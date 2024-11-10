package it.unibo.the100dayswar.model.bot.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.BotStrategy;
import it.unibo.the100dayswar.model.playeraction.api.GenericPlayerCommand;
import it.unibo.the100dayswar.model.playeraction.impl.MovementUnitCommand;
import it.unibo.the100dayswar.model.playeraction.impl.PurchaseUnitCommand;
import it.unibo.the100dayswar.model.playeraction.impl.UpgradeUnitCommand;

/**
 * An implementation of the BotStrategy interface that represents a simple strategy
 * that evaluate the best move assigning a score to each possible move and 
 * after this it will choose the move with the highest score.
 */
public class SimpleBotStrategy implements BotStrategy {
    private static final long serialVersionUID = 1L;
    private final Map<GenericPlayerCommand<?>, Integer> scoreMove;
    /**
     * Constructor for the strategy of the bot.
     */
    public SimpleBotStrategy() {
        scoreMove = new HashMap<>();
        fillScoreMove();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final BotPlayer botPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }
    /** 
     * This method fills the map with the possible moves to evaluate and a placeholder score
     * that will change based on the current state of the bot.
     */
    private void fillScoreMove() {
        scoreMove.put(new MovementUnitCommand(), 0);
        scoreMove.put(new UpgradeUnitCommand(), 0);
        scoreMove.put(new PurchaseUnitCommand(), 0);
    }
}
