package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.BasicTower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Class that implements a basic type of tower extending 
 * AbstractTower.
 */
public class BasicTowerImpl extends AbstractTower implements BasicTower {
    private static final long serialVersionUID = 1L;

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
