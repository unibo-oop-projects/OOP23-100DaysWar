package it.unibo.the100dayswar.model.unit.impl;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An abstract implementation that contains all the common features of the game units.
 */
public abstract class UnitImpl implements Unit {

    private static final int DEFAULT_LEVEL = 1;

    private int health;
    private int level;
    private final int costToBuy;
    private final int costToUpgrade;
    private final int maxLevel;
    private final Player owner;

    /**
     * Constructor from the given parameters for a generic Unit.
     * 
     * @param owner the player that owns this unit
     * @param health health points
     * @param costToBuy cost to buy
     * @param costToUpgrade cost to upgrade
     * @param maxLevel maximum level
      */
    public UnitImpl(final Player owner, final int health, final int costToBuy, final int costToUpgrade, final int maxLevel) {
        try {
            this.owner = owner.copy();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Clone not supported", e);
        }
        this.health = health;
        this.level = DEFAULT_LEVEL;
        this.costToBuy = costToBuy;
        this.costToUpgrade = costToUpgrade;
        this.maxLevel = maxLevel;
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
    public abstract void performAttack();
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
    public void upgrade() {
        if (!canUpgrade()) {
            throw new IllegalStateException();
        }
        this.level++;
    }
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
        try {
            return this.owner.copy();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Clone not supported", e);
        }
    }
    /**
     * Method that checks if the object can be upgraded.
     * 
     * @return true if the object can be upgraded, false otherwise.
      */
      private boolean canUpgrade() {
        return this.level < this.maxLevel;
    }
}
