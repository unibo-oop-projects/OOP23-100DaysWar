package it.unibo.the100dayswar.model.tower.api;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.impl.AbstractTower;
import it.unibo.the100dayswar.model.tower.impl.AdvancedTowerImpl;
import it.unibo.the100dayswar.model.tower.impl.BasicTowerImpl;

public interface TowerFactory {
    static AbstractTower buildTower(final Player owner,final TowerType towerType, final Cell position) {
        switch (towerType){
            case BASIC:
                return new BasicTowerImpl(owner, position);

            case ADVANCED:
                return new AdvancedTowerImpl(owner, position);
            
            default:
                throw new IllegalArgumentException("Tower Type unknow");

        }
    };
}
