package it.unibo.the100dayswar.model.player.impl;

import java.util.Set;
import java.util.Collections;

import it.unibo.the100dayswar.model.map.api.BuildableCell;
import it.unibo.the100dayswar.model.player.api.BankAccount;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An immutable implementation of the Player interface.
 */
public final class ImmutablePlayer implements Player {

    private final String username;
    private final BankAccount bankAccount;
    private final Set<Unit> units;
    private final BuildableCell spawnPoint;

    /**
     * Constructor from the given parameters for a generic Player.
     * 
     * @param player the player to be copied
     */
    public ImmutablePlayer(final Player player) {
        this.username = player.getUsername();
        this.bankAccount = player.getBankAccount();
        this.spawnPoint = player.getSpawnPoint();
        this.units = player.getUnits();
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return String.copyValueOf(username.toCharArray());
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public BankAccount getBankAccount() {
        return new BankAccountImpl(this.bankAccount);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public BuildableCell getSpawnPoint() {
        return this.spawnPoint;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public Set<Unit> getUnits() {
        return Collections.unmodifiableSet(this.units);
    }
}
