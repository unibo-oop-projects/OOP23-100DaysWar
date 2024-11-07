package it.unibo.the100dayswar.model.bot.impl;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.BotStrategy;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.impl.AbstractPlayer;
import it.unibo.the100dayswar.model.unit.api.Buyable;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * A simple implementation of a bot player that uses a strategy
 * to decide which move is the best with simple checks and calculations.
 */
public class SimpleBot extends AbstractPlayer implements BotPlayer {
    private static final long serialVersionUID = 1L;

    private BotStrategy strategy;

    /**
     * Constructor for the bot player with the given parameters.
     * 
     * @param username the username of the bot player
     * @param spawn the spawn cell of the bot player
     */
    public SimpleBot(final String username, final BuildableCell spawn) {
        super(username, spawn);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void makeMove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeMove'");
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public BotStrategy getStrategy() {
        return this.strategy;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void setStrategy(final BotStrategy strategy) {
        this.strategy = strategy;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void buyUnit(final Unit unit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyUnit'");
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void upgradeUnit(final Buyable unit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upgradeUnit'");
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void moveUnit(final Movable unit, final Cell destination) {
        unit.movementRequest(destination);
    }
}
