package it.unibo.the100dayswar.model.unit;

/**
 * An abstract object that contains all the common features of the game units.
 */
public abstract class Unit implements Buyable, Combatant {

    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_LEVEL = 1;

    private int health;
    private int level;
    private final int costToBuy;
    private final int costToUpgrade;
    private final int maxLevel;

    /**
     * Constructor from the given parameters for a generic Unit.
     * 
     * @param costToBuy cost to buy
     * @param costToUpgrade cost to upgrade
     * @param maxLevel maximum level
      */
    public Unit(final int costToBuy, final int costToUpgrade, final int maxLevel) {
        this.health = DEFAULT_HEALTH;
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
     * Method that checks if the object can be upgraded.
     * 
     * @return true if the object can be upgraded, false otherwise.
      */
    private boolean canUpgrade() {
        return this.level < this.maxLevel;
    }
}
