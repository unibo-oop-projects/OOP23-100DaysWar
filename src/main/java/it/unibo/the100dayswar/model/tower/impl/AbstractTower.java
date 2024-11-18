package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.fight.impl.GenericBattleCommand;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.unit.api.Combatant;
import it.unibo.the100dayswar.model.unit.impl.UnitImpl;

/**
 * Abstract class representing a Tower in the game.
 */
public abstract class AbstractTower extends UnitImpl implements Tower {
    private static final long serialVersionUID = 1L;

    private static final int MAX_LEVEL = 4;
    private final TowerType towerType;
    private final Cell position;

    /**
     * Constructs a Tower with the specified position, level, and tower type.
     *
     * @param towerType the type of the tower
     * @param owner the owner of the tower
     * @param health the health of the tower
     * @param position the position of the tower
     * @param costToBuy the cost to buy the tower
     * @param costToUpgrade the cost to upgrade the tower
     */
    public AbstractTower(
        final TowerType towerType, 
        final Player owner, 
        final int health,
        final Cell position,
        final int costToBuy,
        final int costToUpgrade
        ) {
        super(owner, health, costToBuy, costToUpgrade, MAX_LEVEL);
        this.position = position;
        this.towerType = towerType;
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
    public Cell getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract int getDamage();

    /**
     * {@inheritDoc}
     */
    @Override
    public TowerType getTowerType() {
        return this.towerType;
    }

    /**
     * Gets the MAX_LEVEL of the tower.
     * 
     * @return the MAX_LEVEL of the tower
     */
    public static int getMaxLevel() {
        return MAX_LEVEL;
    }
}
