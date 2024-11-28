package it.unibo.the100dayswar.model.bot;

import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.DecisionMaker;
import it.unibo.the100dayswar.model.bot.impl.ActionType;
import it.unibo.the100dayswar.model.bot.impl.DecisionMakerImpl;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BotTest {

    private static final int MAP_SIZE = 10;
    private BotPlayer bot;

    @BeforeEach
    void setUp() {
        bot = new SimpleBot(new MapManagerImpl(new GameMapBuilderImpl(MAP_SIZE, MAP_SIZE)
                .initializeBuildableCells()
                .addSpawnCells()
                .addObstacles(2)
                .addBonusCell(2)));
    }

    @Test
    void evaluationTest() {
        final DecisionMaker decisionMaker = new DecisionMakerImpl();
        decisionMaker.evaluateMoves(bot);
        final Optional<ActionType> bestMove = decisionMaker.getBestMoveType();
        // The first move should be the purchase of a soldier
        assertEquals(bestMove.get(), ActionType.PURCHASE_SOLDIER);
        // The bot should have 1 soldier after making the first move
        bot.makeMove();
        assertEquals(bot.getSoldiers().size(), 1);
        // The best move should still be the purchase of a soldier because
        // th evaluation function is not called again
        assertEquals(decisionMaker.getBestMoveType().get(), ActionType.PURCHASE_SOLDIER);
        decisionMaker.evaluateMoves(bot);
        assertEquals(decisionMaker.getBestMoveType().get(), ActionType.MOVE_UNIT);
        bot.makeMove();
        assertEquals(bot.getSoldiers().size(), 1);
        // This method for now does not change the position of the soldier
        // because needs the add of the map manager to notify the movement request
        //assertFalse(bot.getSpawnPoint().equals(bot.getSoldiers().stream().findAny().get().getPosition()));
    }

}
