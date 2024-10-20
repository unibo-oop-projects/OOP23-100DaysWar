package it.unibo.the100dayswar.model.unit.impl;

import it.unibo.the100dayswar.model.unit.api.Movable;

/**
 * The phisical structure that can move around the map, attack other soldiers, towers and conquer territories.
 */
public class Soldier extends Unit implements Movable {
    private static final int SOLDIER_DEFAULT_COST = 50;
    private static final int SOLDIER_DEFAULT_COST_TO_UPGRADE = 30;
    private static final int SOLDIER_MAX_LEVEL = 3;
    /**
     * Constructor for the soldier.
     */
    public Soldier() {
        super(SOLDIER_DEFAULT_COST, SOLDIER_DEFAULT_COST_TO_UPGRADE, SOLDIER_MAX_LEVEL);
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
    /**
     * {@inheritDoc}
     */
    @Override
    public void performAttack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'performAttack'");
    }
}
