package it.unibo.the100dayswar.model.player.impl;

import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.command.api.GenericPlayerCommand;
import it.unibo.the100dayswar.model.command.impl.PurchaseUnitCommand;
import it.unibo.the100dayswar.model.command.impl.UpgradeUnitCommand;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.unit.api.Buyable;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An implementation of the HumanPlayer interface with the specific
 * actions that a player can perform during the game.
 */
public class HumanPlayerImpl extends AbstractPlayer implements HumanPlayer {
    private static final long serialVersionUID = 1L;
    /**
     * Constructor for the human player from the given parameters.
     * 
     * @param username the username of the player
     * @param spawnPoint the spawn point of the player
      */
    public HumanPlayerImpl(final String username, final BuildableCell spawnPoint) {
        super(username, spawnPoint);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void buyUnit(final Unit unit) {
        final GenericPlayerCommand<Unit> command = new PurchaseUnitCommand();
        command.execute(this, unit);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void upgradeUnit(final Buyable unit) {
        final GenericPlayerCommand<Buyable> command = new UpgradeUnitCommand();
        command.execute(this, unit);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void moveUnit(final Movable unit, final Cell destination) {
        unit.move(destination);
    }
}
