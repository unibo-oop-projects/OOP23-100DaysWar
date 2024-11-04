package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.BasicTower;
import it.unibo.the100dayswar.model.tower.api.TowerType;

/**
 * Class that implements a basic type of tower extending 
 * AbstractTower.
 */
public class BasicTowerImpl extends AbstractTower implements BasicTower {
    private static final int HEALTH_MULTYPLIER_BASIC = 10;
    private static final int UPGRADE_MULTYPLIER_BASIC = 2;

    /**
     * Constructs a basic tower.
     * 
     * @param owner the owner of the basic tower
     * @param position the positon of basic tower in the map
     */
    public BasicTowerImpl(final Player owner, final Cell position) {
        super(TowerType.BASIC, owner,
        TowerType.BASIC.getPrice() * HEALTH_MULTYPLIER_BASIC, 
        position, 
        TowerType.BASIC.getPrice(), 
        TowerType.BASIC.getPrice() * UPGRADE_MULTYPLIER_BASIC);
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
