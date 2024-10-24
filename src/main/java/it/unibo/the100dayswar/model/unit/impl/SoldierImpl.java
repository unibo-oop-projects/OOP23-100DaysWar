package it.unibo.the100dayswar.model.unit.impl;

import it.unibo.the100dayswar.model.map.api.BonusCell;
import it.unibo.the100dayswar.model.map.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Soldier;

/**
 * A concrete implementation of the Soldier interface.
 */
public class SoldierImpl extends UnitImpl implements Soldier {
    private static final int DEFAULT_COST = 50;
    private static final int DEFAULT_COST_TO_UPGRADE = 30;
    private static final int MAX_LEVEL = 3;
    private static final int DEFAULT_HEALTH = 100;
    private Cell position;
    /**
     * Constructor for the soldier.
     * 
     * @param owner the player that owns this soldier
     */
    public SoldierImpl(final Player owner) {
        super(owner, DEFAULT_COST, DEFAULT_HEALTH, DEFAULT_COST_TO_UPGRADE, MAX_LEVEL);
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
        if(this.canMove(cell)) {
            this.position = cell;
            
            if(cell instanceof BonusCell) {
                try {
                    ((BonusCell) cell).activateBonus(this.getOwner());
                } catch (CloneNotSupportedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMove(final Cell target) {
        return this.getPosition().isFree() && target.isFree();
    }
    /**
     * A method that returns the current position of the soldier.
     * 
     * @return the current position of the soldier
     */
    private Cell getPosition() {
        return this.position;
    }
}
