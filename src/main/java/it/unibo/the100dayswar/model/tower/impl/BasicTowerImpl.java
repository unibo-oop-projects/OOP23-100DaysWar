package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.TowerType;

public class BasicTowerImpl extends AbstractTower {
    private static final int HEALTH_MULTYPLIER = 10;
    private static final int UPGRADE_MULTYPLIER = 2;

    public BasicTowerImpl(final Player owner, final Cell position) {
        super(TowerType.BASIC, owner,
        TowerType.BASIC.getPrice() * HEALTH_MULTYPLIER, 
        position, 
        TowerType.BASIC.getPrice(), 
        TowerType.BASIC.getPrice() * UPGRADE_MULTYPLIER);
    }

    @Override
    public int getDamage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDamage'");
    }
    
}
