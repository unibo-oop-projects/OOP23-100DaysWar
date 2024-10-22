package it.unibo.the100dayswar.model.player.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.the100dayswar.model.map.api.BuildableCell;
import it.unibo.the100dayswar.model.player.api.BankAccount;
import it.unibo.the100dayswar.model.player.api.MutablePlayer;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Soldier;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An abstract class that implements the Player interface.
 */
public abstract class PlayerAbs implements MutablePlayer {

    private final String username;
    private final BankAccount bankAccount;
    private final Set<Unit> units;
    private final BuildableCell spawnPoint;

    /**
     * Constructor for the human player from the given parameters.
     * 
     * @param username the username of the player
     * @param spawnPoint the spawn point of the player
      */
    public PlayerAbs(final String username, final BuildableCell spawnPoint) {
        this.username = username;
        this.bankAccount = new BankAccountImpl();
        this.units = new HashSet<>();
        this.spawnPoint = spawnPoint;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public abstract void buyUnit(Unit unit);
    /** 
     * {@inheritDoc}
     */
    @Override
    public abstract void upgradeUnit(Unit unit);
    /** 
     * {@inheritDoc}
     */
    @Override
    public abstract void moveUnit(Movable unit);
    /** 
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return String.copyValueOf(this.username.toCharArray());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Unit> getUnits() {
        return Collections.unmodifiableSet(units);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Soldier> getSoldiers() {
        return units.stream()
            .filter(u -> u instanceof Soldier)
            .map(u -> (Soldier) u)
            .collect(Collectors.toUnmodifiableSet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUnit(final Unit unit) {
        units.remove(unit);
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
    public void earnResources(final int amount) {
        this.bankAccount.earn(amount);
    }
}
