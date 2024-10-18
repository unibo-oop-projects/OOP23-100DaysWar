package it.unibo.the100dayswar.model.unit;

/**
 * The phisical structure that can move around the map, attack other soldiers, towers and conquer territories.
 */
public class Soldier extends Unit implements Movable {
    /**
     * Constructor for the soldier.
     * 
     * @param baseCost the cost of the soldier.
     * @param maxLevel the maximum level of the soldier.
     */
    public Soldier(final int baseCost, final int maxLevel) {
        super(baseCost, maxLevel, maxLevel, maxLevel, maxLevel);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void takeDamage(final int damage) {
        setHealth(this.currentHealth() - damage);
    }
}
