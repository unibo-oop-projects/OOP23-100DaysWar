package it.unibo.the100dayswar.model.bot.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.BotStrategy;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.BuildableCellImpl;
import it.unibo.the100dayswar.model.player.impl.AbstractPlayer;

/**
 * A simple implementation of a bot player that uses a strategy
 * to decide which move is the best with simple checks and calculations.
 */
public class SimpleBot extends AbstractPlayer implements BotPlayer {
    private static final long serialVersionUID = 1L;
    private static final String BOT_NAME = "Bot1";

    private final BuildableCell enemySpawnPoint;
    private final Set<Cell> gameMapCells;
    private final BotStrategy strategy;

    /**
     * Constructor for the bot player.
     *
     * @param spawnPoint      the spawn point of the bot player
     * @param enemySpawnPoint the spawn point of the enemy
     * @param gameMapCells    the game map
     */
    public SimpleBot(final BuildableCell spawnPoint,
            final BuildableCell enemySpawnPoint,
            final Stream<Cell> gameMapCells) {
        super(BOT_NAME, spawnPoint);
        this.strategy = new SimpleBotStrategy();
        this.enemySpawnPoint = new BuildableCellImpl(enemySpawnPoint);
        this.gameMapCells = gameMapCells.collect(Collectors.toUnmodifiableSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeMove() {
        strategy.apply(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildableCell enemySpawnPoint() {
        return new BuildableCellImpl(enemySpawnPoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Cell> getAllCells() {
        return new HashSet<>(gameMapCells);
    }
}
