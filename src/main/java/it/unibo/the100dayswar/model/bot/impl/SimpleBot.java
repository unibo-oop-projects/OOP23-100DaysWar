package it.unibo.the100dayswar.model.bot.impl;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.BotStrategy;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.impl.AbstractPlayer;
import it.unibo.the100dayswar.model.unit.api.Buyable;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Unit;

public class SimpleBot extends AbstractPlayer implements BotPlayer {

    private BotStrategy strategy;

    public SimpleBot(String username, BuildableCell spawn) {
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
    public void setStrategy(BotStrategy strategy) {
        this.strategy = strategy;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void buyUnit(Unit unit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyUnit'");
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void upgradeUnit(Buyable unit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upgradeUnit'");
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void moveUnit(Movable unit, Cell destination) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveUnit'");
    }
}
