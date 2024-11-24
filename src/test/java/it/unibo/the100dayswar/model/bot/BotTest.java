package it.unibo.the100dayswar.model.bot;

import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.DecisionMaker;
import it.unibo.the100dayswar.model.bot.impl.ActionType;
import it.unibo.the100dayswar.model.bot.impl.DecisionMakerImpl;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;
import it.unibo.the100dayswar.model.cell.api.Cell;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BotTest {

    private static final int MAP_SIZE = 10;

    GameMap map;
    private Player enemy;
    private BotPlayer bot;

    @BeforeEach
    private void setUp() {
        map = createMap();
        final Cell enemySpawnPoint = map.getAllCells()
            .filter(c -> c.getPosition().getX() == 0)
            .filter(c -> c.isSpawn())
            .findFirst().get();

        final Cell spawnPoint = map.getAllCells()
            .filter(c -> c.getPosition().getX() == MAP_SIZE - 1
            && c.isSpawn()
            && !c.equals(enemySpawnPoint))
            .findFirst().get();

        enemy = new PlayerImpl("Enemy", enemySpawnPoint);
        bot = new SimpleBot(spawnPoint, enemy.getSpawnPoint(), map.getAllCells());
    }

    @Test
    void evaluationTest() {
        final DecisionMaker decisionMaker = new DecisionMakerImpl();
        decisionMaker.evaluateMoves(bot);
        Optional<ActionType> bestMove = decisionMaker.getBestMoveType();
        System.out.println(bestMove.isPresent() ? "Best move: " + decisionMaker.getBestMoveType() : "No best move");
    }

    private GameMap createMap() {
        return new GameMapBuilderImpl(MAP_SIZE, MAP_SIZE)
                .initializeBuildableCells()
                .addSpawnCells()
                .addObstacles(5)
                .addBonusCell(2)
                .build();
    }

}
