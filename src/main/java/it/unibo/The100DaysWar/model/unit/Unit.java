package it.unibo.the100dayswar.model.unit;

/**
 * An abstract object that contains all the common features of the game units.
 */
public abstract class Unit implements Buyable, Combatant {

    private int health;
    private int level;
    private final int costToBuy;
    private final int costToUpgrade;
    private final int maxLevel;

    /**
     * Constructor from the given parameters for a generic Unit.
     * 
     * @param health health
     * @param level level
     * @param costToBuy cost to buy
     * @param costToUpgrade cost to upgrade
     * @param maxLevel maximum level
      */
    public Unit(final int health, final int level, final int costToBuy, final int costToUpgrade, final int maxLevel) {
        this.health = health;
        this.level = level;
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
    public void performAttack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'performAttack'");
    }
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
        return this.costToUpgrade;
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
    public boolean upgrade() {
        if (canUpgrade()) {
            this.level++;
            return true;
        }
        return false;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void downgrade() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'downgrade'");
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
