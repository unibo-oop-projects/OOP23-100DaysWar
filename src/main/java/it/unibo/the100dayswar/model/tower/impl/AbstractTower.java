package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.unit.impl.UnitImpl;

/**
 * Abstract class representing a Tower in the game.
 */
public abstract class AbstractTower extends UnitImpl implements Tower {
    private static final int MAX_LEVEL = 4;
    private final TowerType towerType;
    private final Cell position;

    /**
     * Constructs a Tower with the specified position, level, and tower type.
     *
     * @param position  the position of the tower
     * @param level     the level of the tower
     * @param towerType the type of the tower
     */
    public AbstractTower(final TowerType towerType, final Player owner, final int health, final Cell position, final int costToBuy, final int costToUpgrade) {
        super(owner, health, costToBuy, costToUpgrade, MAX_LEVEL);
        this.position = position;
        this.towerType = towerType;
    }

    @Override
    public void performAttack() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Cell getCell() {
        return this.position;
    }

    @Override
    public abstract int getDamage();

    @Override
    public TowerType getTowerType() {
        return this.towerType;
    }

    
}
