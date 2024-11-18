package it.unibo.the100dayswar.model.soldier.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.fight.impl.GenericBattleCommand;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.unit.api.Combatant;
import it.unibo.the100dayswar.model.unit.impl.UnitImpl;

/**
 * A concrete implementation of the Soldier interface.
 */
public class SoldierImpl extends UnitImpl implements Soldier {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_COST_TO_UPGRADE = 30;
    private static final int MAX_LEVEL = 3;
    private static final int DEFAULT_HEALTH = 100;
    private static final int INCREASE_HEALTH = 50;

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
    public void performAttack(final Combatant target) {
        new GenericBattleCommand<>().execute(this, target);
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
    public void movementRequest(final Cell target) {
        notifyObservers(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgrade() {
        if (this.level() < MAX_LEVEL) {
            this.level();
            this.setHealth(DEFAULT_HEALTH + INCREASE_HEALTH * this.level() - 1);
        }
    }
}
