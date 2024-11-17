package it.unibo.the100dayswar.model.bot.impl;

import java.util.Set;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.BotStrategy;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.impl.AbstractPlayer;

/**
 * A simple implementation of a bot player that uses a strategy
 * to decide which move is the best with simple checks and calculations.
 */
public class SimpleBot extends AbstractPlayer implements BotPlayer {
    private static final long serialVersionUID = 1L;
    private static final String BOT_USERNAME = "Bot1";

    private final BotStrategy strategy;
    /**
     * Constructor for the bot player with the given parameters.
     *
     * @param spawn the spawn cell of the bot player
     */
    public SimpleBot(final BuildableCell spawn) {
        super(BOT_USERNAME, spawn);
        this.strategy = new SimpleBotStrategy();
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void makeMove() {
        this.strategy.apply(this);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public Cell enemySpawnPoint() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enemySpawnPoint'");
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public Set<Cell> getAllCells() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCells'");
    }
}
