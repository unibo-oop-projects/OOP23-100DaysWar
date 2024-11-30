package it.unibo.the100dayswar.model.bot;

import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.DecisionMaker;
import it.unibo.the100dayswar.model.bot.impl.ActionType;
import it.unibo.the100dayswar.model.bot.impl.DecisionMakerImpl;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BotTest {

    private static final int MAP_SIZE = 10;

    private MapManager mapManager;
    private BotPlayer bot;
    private DecisionMaker decisionMaker;

    @BeforeEach
    void setUp() {
        mapManager = new MapManagerImpl(new GameMapBuilderImpl(MAP_SIZE, MAP_SIZE));
        bot = new SimpleBot(mapManager);
        decisionMaker = new DecisionMakerImpl();
    }

    @Test
    void initializationTest() {
        final int initialBalance = 1000;
        assertEquals(bot.getUnits(), Set.of(), 
            "The bot should not have any units at the beginning.");
        assertEquals(bot.getSoldiers(), Set.of(),
            "The bot should not have any soldiers at the beginning.");
        assertEquals(bot.getTowers(), Set.of(),
            "The bot should not have any towers at the beginning.");
        assertEquals(bot.getBankAccount().getBalance(), initialBalance,
            "The initial balance of the bot should be 1000.");
        assertEquals(bot.getSpawnPoint(), mapManager.getBotSpawn(),
            "The bot's spawn cell should be the same as the one retrieved from the map manager.");
    }

    @Test
    void evaluationTest() {
        assertEquals(true, bot.getSpawnPoint().isSpawn(),
            "The bot's spawn cell should be marked as spawn.");
        assertEquals(true, bot.getEnemySpawnPoint().isSpawn(),
            "The enemy's spawn cell should be marked as spawn.");

        decisionMaker.evaluateMoves(bot);
        Optional<ActionType> bestMove = decisionMaker.getBestMoveType();
        assertEquals(bestMove.get(), ActionType.PURCHASE_SOLDIER,
            "The best move should be to purchase a soldier.");

        bot.makeMove();
        assertEquals(bot.getSoldiers().size(), 1,
            "The bot should have one soldier after the first purchase.");
        final Soldier soldier = bot.getSoldiers().stream().findFirst().get();
        assertEquals(bot.getSpawnPoint(), soldier.getPosition(),
            "The soldier should be positioned on the bot's spawn cell.");

        assertEquals(true, bot.getSpawnPoint().isSpawn(),
            "The bot's spawn cell should still be marked as spawn.");
        assertEquals(true, bot.getEnemySpawnPoint().isSpawn(),
            "The enemy's spawn cell should still be marked as spawn.");

        decisionMaker.evaluateMoves(bot);
        bestMove = decisionMaker.getBestMoveType();
        assertEquals(bestMove.get(), ActionType.MOVE_UNIT,
            "The best move should be to move the unit after purchasing the soldier.");

        bot.makeMove();
        assertEquals(bot.getSoldiers().size(), 1,
            "The number of soldiers should remain unchanged after the movement.");
        assertNotEquals(soldier.getPosition(), bot.getSpawnPoint(),
            "The soldier's position should change after the movement.");

        assertEquals(true, bot.getSpawnPoint().isSpawn(),
            "The bot's spawn cell should still be marked as spawn.");
        assertEquals(true, bot.getEnemySpawnPoint().isSpawn(),
            "The enemy's spawn cell should still be marked as spawn.");
    }
}
