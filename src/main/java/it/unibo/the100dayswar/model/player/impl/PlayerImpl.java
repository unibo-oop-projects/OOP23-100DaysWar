package it.unibo.the100dayswar.model.player.impl;

import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Buyable;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Implemantation of the abstract class player.
 */
public class PlayerImpl extends AbstractPlayer {
    /**
     * Constructor that produce a player from the given parameters.
     * 
     * @param username usarname of the player
     * @param spawnPoint spawn point of the player
     */
    public PlayerImpl(final String username, final BuildableCell spawnPoint) {
        super(username, spawnPoint);
    }

    /**
     * Constructor that produce a player from the given player.
     * 
     * @param player
     */
    public PlayerImpl(final Player player) {
        super(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyUnit(final Buyable unit) {
        // TODO Auto-generated method stub.
        throw new UnsupportedOperationException("Unimplemented method 'buyUnit'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgradeUnit(final Unit unit) {
        // TODO Auto-generated method stub.
        throw new UnsupportedOperationException("Unimplemented method 'upgradeUnit'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveUnit(final Movable unit) {
        // TODO Auto-generated method stub.
        throw new UnsupportedOperationException("Unimplemented method 'moveUnit'");
    }
}
