package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.TowerType;

/**
 * Class that implements an advanced type of tower extending 
 * AbstractTower.
 */
public class AdvancedTowerImpl extends AbstractTower {
    private static final int HEALTH_MULTYPLIER_ADVANCED = 12;
    private static final int UPGRADE_MULTYPLIER_ADVANCED = 2;

    /**
     * Constructs an advanced tower.
     * 
     * @param owner the owner of the advanced tower
     * @param position the position of the advanced tower in the map
     */
    public AdvancedTowerImpl(final Player owner, final Cell position) {
        super(TowerType.ADVANCED, owner,
        TowerType.ADVANCED.getPrice() * HEALTH_MULTYPLIER_ADVANCED, 
        position, 
        TowerType.ADVANCED.getPrice(), 
        TowerType.ADVANCED.getPrice() * UPGRADE_MULTYPLIER_ADVANCED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDamage'");
    }
}
