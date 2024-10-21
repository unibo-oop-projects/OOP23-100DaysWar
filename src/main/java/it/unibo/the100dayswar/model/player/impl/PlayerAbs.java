package it.unibo.the100dayswar.model.player.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.the100dayswar.model.player.api.BankAccount;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.impl.Soldier;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An abstract class that implements the Player interface.
 */
public abstract class PlayerAbs implements Player {

    private final String username;
    private final List<Unit> units;
    private final BankAccount bankAccount;

    /**
     * Constructor for the human player from the given parameters.
     * 
     * @param username the username of the player
      */
    public PlayerAbs(final String username) {
        this.username = username;
        this.bankAccount = new BankAccountImpl();
        this.units = new LinkedList<>();
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
    public BankAccount getBankAccount() {
        return new BankAccountImpl(this.bankAccount);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.username;
    }
    /**
     * {@inheritDoc}
     */
    public List<Unit> getUnits() {
        return Collections.unmodifiableList(units);
    }
    /**
     * {@inheritDoc}
     */
    public List<Unit> getSoldiers() {
        return units.stream()
                    .filter(u -> u instanceof Soldier)
                    .collect(Collectors.toUnmodifiableList());
    }
    /**
     * {@inheritDoc}
     */
    public void addUnit(final Unit unit) {
        units.add(unit);
    }
    /**
     * {@inheritDoc}
     */
    public void removeUnit(final Unit unit) {
        units.remove(unit);
    }
}
