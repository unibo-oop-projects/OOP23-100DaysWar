package it.unibo.the100dayswar.model.unit.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.unit.api.Combatant;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An abstract implementation that contains all the common features of the game
 * units.
 */
public abstract class UnitImpl implements Unit {
    private static final long serialVersionUID = 1L;

    private int health;
    private int level;
    private final int costToBuy;
    private final int costToUpgrade;
    private final int maxLevel;
    private final Player owner;
    private final List<Observer<Pair<Unit, Cell>>> observers;

    /**
     * Constructor from the given parameters for a generic Unit.
     * 
     * @param owner         the player that owns this unit
     * @param health        health points
     * @param costToBuy     cost to buy
     * @param costToUpgrade cost to upgrade
     * @param maxLevel      maximum level
     */
    public UnitImpl(final Player owner, final int health, final int costToBuy, final int costToUpgrade,
            final int maxLevel) {
        this.owner = new PlayerImpl(owner);
        this.health = health;
        this.level = DEFAULT_LEVEL;
        this.costToBuy = costToBuy;
        this.costToUpgrade = costToUpgrade;
        this.maxLevel = maxLevel;
        this.observers = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int currentHealth() {
        return this.health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void takeDamage(final int damage) {
        setHealth(this.currentHealth() - damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void performAttack(Combatant target);

    /**
     * {@inheritDoc}
     */
    @Override
    public int costToBuy() {
        return this.costToBuy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int costToUpgrade() {
        return this.costToUpgrade * this.level();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int level() {
        return this.level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canUpgrade() {
        return this.level < this.maxLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void upgrade();

    /**
     * {@inheritDoc}
     */
    @Override
    public void downgrade() {
        if (this.level == 1) {
            throw new IllegalStateException();
        }
        this.level--;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getOwner() {
        return new PlayerImpl(this.owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attach(final Observer<Pair<Unit, Cell>> observer) {
        this.observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detach(final Observer<Pair<Unit, Cell>> observer) {
        this.observers.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers(final Cell target) {
        observers.forEach(observer -> observer.update(new Pair<>(this, target)));
    }
}
