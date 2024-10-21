package it.unibo.the100dayswar.model.unit.impl;

import it.unibo.the100dayswar.model.map.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Movable;

/**
 * The phisical structure that can move around the map, attack other soldiers, towers and conquer territories.
 */
public class Soldier extends UnitImpl implements Movable {
    private static final int SOLDIER_DEFAULT_COST = 50;
    private static final int SOLDIER_DEFAULT_COST_TO_UPGRADE = 30;
    private static final int SOLDIER_MAX_LEVEL = 3;
    private Cell position;
    /**
     * Constructor for the soldier.
     * 
     * @param owner the player that owns this soldier
     */
    public Soldier(final Player owner) {
        super(owner, SOLDIER_DEFAULT_COST, SOLDIER_DEFAULT_COST_TO_UPGRADE, SOLDIER_MAX_LEVEL);
        this.position = owner.getSpawnPoint();
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
    public void move(final Cell cell) {
        this.position = cell;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getCell() {
        return this.position;
    }
}
