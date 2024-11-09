package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.AdvancedTower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Class that implements an advanced type of tower extending 
 * AbstractTower.
 */
public class AdvancedTowerImpl extends AbstractTower implements AdvancedTower {
    private static final long serialVersionUID = 1L;

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

     /**
     * {@inheritDoc}
     */
    @Override
    public void attach(final Observer<Pair<Unit, Cell>> observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attach'");
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void detach(final Observer<Pair<Unit, Cell>> observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'detach'");
    }
}
