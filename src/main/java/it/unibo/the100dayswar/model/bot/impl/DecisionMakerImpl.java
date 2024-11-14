package it.unibo.the100dayswar.model.bot.impl;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.the100dayswar.commons.utilities.impl.Score;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.DecisionMaker;

/** 
 * Implementation of the decision maker.
 */
public class DecisionMakerImpl implements DecisionMaker {
    private static final long serialVersionUID = 1L;
    private static final int NON_PERFORMABLE_SCORE = -1;
    private final Map<ActionType, Score> scoreMove;
    /**
     * Constructor of the class.
     */
    public DecisionMakerImpl() {
        this.scoreMove = new EnumMap<>(ActionType.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void evaluateMoves(final BotPlayer botPlayer) {
        scoreMove.clear(); // Placeholder
        scoreMove.put(ActionType.PURCHASE_SOLDIER, new Score(NON_PERFORMABLE_SCORE)); // Placeholder
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ActionType> getBestMoveType() {
        return scoreMove.entrySet().stream()
                .filter(entry -> entry.getValue().getValue() >= 0)
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
