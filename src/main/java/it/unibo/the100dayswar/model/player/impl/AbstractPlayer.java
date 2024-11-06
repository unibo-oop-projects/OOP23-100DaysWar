package it.unibo.the100dayswar.model.player.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.the100dayswar.commons.utilities.api.ResourceGenerator;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.impl.BuildableCellImpl;
import it.unibo.the100dayswar.model.player.api.BankAccount;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Buyable;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Soldier;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An abstract class that implements the Player interface.
 */
public abstract class AbstractPlayer implements Player {
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
    public AbstractPlayer(final String username, final BuildableCell spawnPoint) {
        this.username = username;
        this.bankAccount = new BankAccountImpl();
        this.units = new HashSet<>();
        this.spawnPoint = new BuildableCellImpl(spawnPoint);
    }
    /**
     * Constructor for the human player from the given player.
     * 
     * @param player player to copy
     */
    public AbstractPlayer(final Player player) {
        this.username = player.getUsername();
        this.bankAccount = player.getBankAccount();
        this.units = player.getUnits();
        this.spawnPoint = player.getSpawnPoint();
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public abstract void buyUnit(Buyable unit);
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
    public BankAccount getBankAccount() {
        return new BankAccountImpl(this.bankAccount);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BuildableCell getSpawnPoint() {
        return new BuildableCellImpl(this.spawnPoint);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void earnResources(final int amount) {
        this.bankAccount.earn(amount);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addUnit(final Unit unit) {
        this.units.add(unit);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void spendResources(final int amount) {
        this.bankAccount.purchase(amount);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Player copy() {
        try {
            return (AbstractPlayer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Clone not supported", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final ResourceGenerator generator) {
        this.earnResources(generator.getAmount());
    }
}
